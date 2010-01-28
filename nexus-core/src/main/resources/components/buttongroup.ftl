<script type="text/javascript">
	$(document).ready(function(){
		$("#btn_save").click(function () { 
	      	form = document.forms[0];
		    form.action = "save";
		    form.submit();
	    });
	    $("#btn_remove").click(function () { 
	        form = document.forms[0];
		    form.action = "remove";
		    form.submit();
	    });
	    $("#btn_start").click(function () { 
	        form = document.forms[0];
		    form.action = "start";
		    form.submit();
	    });
	    $("#btn_stop").click(function () { 
	        form = document.forms[0];
		    form.action = "stop";
		    form.submit();
	    });
	    
	});
</script>
${buttongroup}