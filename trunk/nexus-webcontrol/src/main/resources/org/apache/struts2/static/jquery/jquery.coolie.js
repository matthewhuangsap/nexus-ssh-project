function addDateInput(obj){
	$(obj).datepicker();  
}

function addChoiceBox(obj,hiddenobj,action,datakind){
	$(obj).choicebox(action,{dk:datakind}, hiddenobj);
}

function addLayoutPanel(obj,obj_ul,obj_li,obj_span,cols,height){
    var persent_width = 100/cols;

    $(obj).css("background","green");
    $(obj_ul).css("border","1px solid #cfdae8")
            .css("border-left","none")
            .css("overflow","hidden")
            .css("background","url(images/linex.gif)");
    $(obj_li).css("width",persent_width+"%")
            .css("*width",persent_width+"%")
            .css("float","left")
    $(obj_span).css("display","block")
            .css("border-left","1px solid #cfdae8")
            .css("height",height)
            .css("font-size","12px")
            .css("line-height","24px")
            .css("padding","0 4px");
}



