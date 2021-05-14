//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidPostIDSave").val("");
	$("#POST")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#hidPostIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "FundingAPI",
		type : type,
		data : $("#FUNDING").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

});

function onItemSaveComplete(response, status) {

	if (status == "success") {

		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#PostGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {

			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {

		$("#alertError").text("Error while saving.");
		$("#alertError").show();

	} else {

		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidPostIDSave").val("");
	$("#POST")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {

	$.ajax({
		url : "FundingAPI",
		type : "DELETE",
		data : "postID=" + $(this).data("postid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {

	if (status == "success") {

		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#PostGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {

			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {

		$("#alertError").text("Error while deleting.");
		$("#alertError").show();

	} else {

		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidPostIDSave").val(
					$(this).closest("tr").find('#hidpostNOUpdate').val());
			$("#postID").val($(this).closest("tr").find('td:eq(0)').text());
			$("#postTitle").val($(this).closest("tr").find('td:eq(1)').text());
			$("#content").val($(this).closest("tr").find('td:eq(2)').text());
			$("#publishedDate").val(
					$(this).closest("tr").find('td:eq(3)').text());
			$("#publishedTime").val(
					$(this).closest("tr").find('td:eq(4)').text());

		});

// CLIENTMODEL=========================================================================
function validateItemForm() {

	// Post Title
	if ($("#postTitle").val().trim() == "") {
		return "Please insert post title.";
	}

	// Content
	if ($("#content").val().trim() == "") {
		return "Please insert content .";
	}

	return true;
}
