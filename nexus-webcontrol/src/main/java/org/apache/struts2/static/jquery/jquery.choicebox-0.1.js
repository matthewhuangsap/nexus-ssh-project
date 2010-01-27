;(function($) {
	$.extend($.fn, {
        choicebox: function(urlOrData, options, hidden) {
//			alert("entry extend function");
			options = $.extend({}, $.Choicebox.defaults, options);	
			return this.each(function() {
//				alert("create Choicebox ");
				new $.Choicebox(this, options, hidden,urlOrData);
			});
		}
	});
	
$.Choicebox = function(input, options ,hidden, urlOrData) {
//	alert("create var");
	var KEY = {
		UP: 38,
		DOWN: 40,
		DEL: 46,
		TAB: 9,
		RETURN: 13,
		ESC: 27,
		COMMA: 188,//逗号
		PAGEUP: 33,
		PAGEDOWN: 34,
		BACKSPACE: 8
	}; //定义了各种可能用到的键盘事件
	
	var lastKeyPressCode; //最后被执行的KeyCode
	var hasFocus =0;
	
//	alert("得到输入文本框，添加 autocomplete属性，并为其赋值为off");
	var $input = $(input).attr("autocomplete", "off").addClass(options.inputClass);
	var $hidden = $("#"+hidden);
	//#######################################################################
	var blockSubmit;
	// prevent form submit in opera when selecting with return key
	// 如果浏览器为opera类型，那么在选中返回值时，防止表单被提交
	$.browser.opera && $(input.form).bind("submit.autocomplete", function() {
		if (blockSubmit) {
			blockSubmit = false;
			return false;
		}
	});
	//#######################################################################
	
//	alert("为输入文本框添加事件");
	// only opera doesn't trigger keydown multiple times while pressed, others don't work with keypress at all
	// 只有opera浏览器使用keypress事件，其它的浏览器都是keydown事件
	$input.bind(($.browser.opera ? "keypress" : "keydown") + ".autocomplete", function(event) {
		lastKeyPressCode = event.keyCode;
		switch(event.keyCode) {
			case KEY.UP:
//				alert("向上");
				event.preventDefault();
				if ( select.visible() ) {
					select.prev();
				} 
				break;
			case KEY.DOWN:
//				alert("向下");
				event.preventDefault();
				if ( select.visible() ) {
					select.next();
				}
				break;
			case KEY.PAGEUP:
//				alert("上翻页");
				break;
			case KEY.PAGEDOWN:
//				alert("下翻页");
				break;
			// matches also semicolon
			case options.multiple && $.trim(options.multipleSeparator) == "," && KEY.COMMA:
			case KEY.TAB:
			case KEY.RETURN:
//				alert("回车");
				//这里要添加查询事件，向后台请示数据的部分写在这里
				if(!select.visible())
					remoteRequest(); //向后台请示数据
				else{
					if( selectCurrent() ) {
						// stop default to prevent a form submit, Opera needs special handling
						event.preventDefault();
						blockSubmit = true;
						return false;
					}
				}
				break;
			case KEY.ESC:
				alert("ESC");
				break;
			default:
				break;
		}
	}).focus(function(){
		//得到焦点
		//这里同样可以触发向后台请示数据
		hasFocus++;
	}).blur(function() {
		//失去焦点
		//将列表藏起来
		hasFocus = 0;
		if (!config.mouseDownOnSelect) {
			hideResultsNow();
		}
	}).click(function() {
		//点击事件
		//此处要向后台请示数据
		if ( hasFocus++ > 1 && !select.visible() ) {
			remoteRequest();
		}
	}).bind("search", function() {
		
	}).bind("flushCache", function() {
		
	}).bind("setOptions", function() {
		
	}).bind("unautocomplete", function() {

	});

	function hideResultsNow() {
		var wasVisible = select.visible();
		select.hide();
		stopLoading();
	};
	
	//设置input的class，移除其Loading样式
	function stopLoading() {
		$input.removeClass(options.loadingClass);
	};
	
	//此方法向后台请求数据 
	function remoteRequest(){
		$input.addClass(options.loadingClass);
		var currentValue = $input.val();
		$.getJSON(urlOrData,{query:currentValue,dk:options.dk,beforDrop:options.beforDrop},function(data){
			var jsondata =eval(data.lists);
			if ( jsondata && jsondata.length && hasFocus ) {
				select.display(jsondata, currentValue);
				select.show();
				stopLoading();
			} else {
				stopLoading();
			}
		});				
	}
	
//	alert("设置配置=》鼠标按下状态");
	var config = {
		mouseDownOnSelect: false
	};
	
//	alert("创建下拉列表框");
	var select = $.Choicebox.Select(options, input, selectCurrent, config);
	
//	alert("选中");
	function selectCurrent() {
		var selected = select.selected();
		if( !selected )
			return false;
		//得到传过来的值	
		var selectdata  = selected;
		//设置显式的值，即文本框的值 
		$input.val(selectdata.value);
		hideResultsNow();
		//设置隐藏文本的值；
		$hidden.val(selectdata.key);
		return true;
	}
	
//	alert("去掉空格");
	function trimWords(value) {
		if ( !value ) {
			return [""];
		}
		var words = value.split( options.multipleSeparator );
		var result = [];
		$.each(words, function(i, value) {
			if ( $.trim(value) )
				result[i] = $.trim(value);
		});
		return result;
	}
	
//	alert("返回最后一个字符");
	function lastWord(value) {
		if ( !options.multiple )
			return value;
		var words = trimWords(value);
		return words[words.length - 1];
	}
};

//创建下拉列表 
$.Choicebox.Select = function (options, input, select, config) {
	//定义一个经过class
	var CLASSES = {
		ACTIVE: "ac_over"
	};
	//定义一些变量
	var listItems,
		active = -1,
		data,
		term = "",
		needsInit = true,
		element,
		list;
		
	// 初始化下拉列表
	function init() {
		//判断是否需要初始化
		if (!needsInit)
			return;
		//创建一个DIV，用来承载UL和LI标签
		element = $("<div/>")
		.hide()
		.addClass(options.resultsClass)
		.css("position", "absolute")
		.appendTo(document.body);
		
		//创建一个UL，用来承载LI显示数据
		list = $("<ul/>").appendTo(element).mouseover( function(event) {
			if(target(event).nodeName && target(event).nodeName.toUpperCase() == 'LI') {
	            active = $("li", list).removeClass(CLASSES.ACTIVE).index(target(event));
			    $(target(event)).addClass(CLASSES.ACTIVE);            
	        }
		}).click(function(event) {
			$(target(event)).addClass(CLASSES.ACTIVE);
			select();
			// TODO provide option to avoid setting focus again after selection? useful for cleanup-on-focus
			input.focus();
			return false;
		}).mousedown(function() {
			config.mouseDownOnSelect = true;
		}).mouseup(function() {
			config.mouseDownOnSelect = false;
		});
		
		//如果用户定义了宽度，则设置下拉框的宽度为用户设置的宽度
		if( options.width > 0 )
			element.css("width", options.width);
			
		//需要初始化变量置为false
		needsInit = false;
	}
	
	//得到当前目标项
	function target(event) {
		var element = event.target;
		while(element && element.tagName != "LI")
			element = element.parentNode;
		// more fun with IE, sometimes event.target is empty, just ignore it then
		if(!element)
			return [];
		return element;
	}
	
	function moveSelect(step) {
		listItems.slice(active, active + 1).removeClass(CLASSES.ACTIVE);
		movePosition(step);
        var activeItem = listItems.slice(active, active + 1).addClass(CLASSES.ACTIVE);
        if(options.scroll) {
            var offset = 0;
            listItems.slice(0, active).each(function() {
				offset += this.offsetHeight;
			});
            if((offset + activeItem[0].offsetHeight - list.scrollTop()) > list[0].clientHeight) {
                list.scrollTop(offset + activeItem[0].offsetHeight - list.innerHeight());
            } else if(offset < list.scrollTop()) {
                list.scrollTop(offset);
            }
        }
	};
	
	function movePosition(step) {
		active += step;
		if (active < 0) {
			active = listItems.size() - 1;
		} else if (active >= listItems.size()) {
			active = 0;
		}
	}
	
	function limitNumberOfItems(available) {
		return options.max && options.max < available
			? options.max
			: available;
	}
	
	
	//
	function fillList() {
		list.empty();
		var max = limitNumberOfItems(data.length);
		for (var i=0; i < max; i++) {
			if (!data[i])
				continue;
			var li = $("<li/>")
			.html(data[i].key +"|" + data[i].value)
			.addClass(i%2 == 0 ? "ac_even" : "ac_odd")
			.appendTo(list)[0];
			$(li).data("ac_data", data[i]);
		}
		listItems = list.find("li");
		if ( options.selectFirst ) {
			listItems.slice(0, 1).addClass(CLASSES.ACTIVE);
			active = 0;
		}
		// apply bgiframe if available
		//这个东西可以用来把DIV等放置到select标签之上
		if ( $.fn.bgiframe )
			list.bgiframe();
	}
	
	
	//这里很特别，返回了select的一些方法
	return {
		display: function(d, q) {
			init();
			data = d;
			term = q;
			fillList();
		},
		next: function() {
			moveSelect(1);
		},
		prev: function() {
			moveSelect(-1);
		},
		pageUp: function() {
			if (active != 0 && active - 8 < 0) {
				moveSelect( -active );
			} else {
				moveSelect(-8);
			}
		},
		pageDown: function() {
			if (active != listItems.size() - 1 && active + 8 > listItems.size()) {
				moveSelect( listItems.size() - 1 - active );
			} else {
				moveSelect(8);
			}
		},
		hide: function() {
			element && element.hide();
			listItems && listItems.removeClass(CLASSES.ACTIVE);
			active = -1;
		},
		visible : function() {
			return element && element.is(":visible");
		},
		current: function() {
			return this.visible() && (listItems.filter("." + CLASSES.ACTIVE)[0] || options.selectFirst && listItems

[0]);
		},
		show: function() {
			var offset = $(input).offset();
			element.css({
				width: typeof options.width == "string" || options.width > 0 ? options.width : $(input).width(),
				top: offset.top + input.offsetHeight,
				left: offset.left
			}).show();
            if(options.scroll) {
                list.scrollTop(0);
                list.css({
					maxHeight: options.scrollHeight,
					overflow: 'auto'
				});
				
                if($.browser.msie && typeof document.body.style.maxHeight === "undefined") {
					var listHeight = 0;
					listItems.each(function() {
						listHeight += this.offsetHeight;
					});
					var scrollbarsVisible = listHeight > options.scrollHeight;
                    list.css('height', scrollbarsVisible ? options.scrollHeight : listHeight );
					if (!scrollbarsVisible) {
						// IE doesn't recalculate width when scrollbar disappears
						listItems.width( list.width() - parseInt(listItems.css("padding-left")) - 

parseInt(listItems.css("padding-right")) );
					}
                }
                
            }
		},
		selected: function() {
			var selected = listItems && listItems.filter("." + CLASSES.ACTIVE).removeClass(CLASSES.ACTIVE);
			return selected && selected.length && $.data(selected[0], "ac_data");
		},
		emptyList: function (){
			list && list.empty();
		},
		unbind: function() {
			element && element.remove();
		}
	};
	
	
};
	
$.Choicebox.defaults = {
	dk:"",
	beforDrop:"",
	resultsClass:"ac_results",
	inputClass:"ac_input",
	loadingClass:"ac_loading",
	width:0,
	selectFirst:true,
	multipleSeparator :", ",
	max:30,
	extraParams:{}
};
		
})(jQuery);