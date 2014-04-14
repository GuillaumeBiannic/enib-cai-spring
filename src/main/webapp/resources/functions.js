

function checkField(url, fieldName, fieldValue) {
	var jsonOutput = {};
	jsonOutput[fieldName] = fieldValue;
	$.postJSON( url + fieldName, jsonOutput, function(data) {
		for(fieldFailure in data.failures) {
			if( fieldFailure ==  fieldName ) {
				fieldValidated( fieldFailure, { valid : false, message : data.failures[fieldFailure] });
			}
			return;
		}
		// No error on the field 
		fieldValidated( fieldName, { valid : true });
	});
}

function fieldValidated(field, result) {
	if (result.valid) {
		$("#" + field + "ControlGroup").removeClass("error");
		$("#" + field + "\\.errors").remove();
		$("#" + field + "\\.icon-ok").remove();
		$('#create').attr("disabled", false);
		$("#" + field).after("<i class='icon-ok' id='" + field + ".icon-ok'></i>");
	} else {
		$("#" + field + "\\.icon-ok").remove();
		$("#" + field + "ControlGroup").addClass("error");
		if ($("#" + field + "\\.errors").length == 0) {
		$("#" + field).after("<span class='help-inline' id='" + field + ".errors'>" + result.message + "</span>");		
		} else {
		$("#" + field + "\\.errors").html("<span class='help-inline' id='" + field + ".errors'>" + result.message + "</span>");		
		}
		$('#create').attr("disabled", true);
	}
}