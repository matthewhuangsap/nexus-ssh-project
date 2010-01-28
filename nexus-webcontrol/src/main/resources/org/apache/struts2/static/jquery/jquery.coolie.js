function addDateInput(obj){
	$(obj).datepicker();  
}

function addChoiceBox(obj,hiddenobj,action,datakind){
	$(obj).choicebox(action,{dk:datakind}, hiddenobj);
}



