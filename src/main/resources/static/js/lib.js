$('.selectpicker').selectpicker({
	style : 'btn-default',
	size : 10
});
$('.form_datetime').datetimepicker({
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1
});

function checkTaskForm(e){
	return false;
}
