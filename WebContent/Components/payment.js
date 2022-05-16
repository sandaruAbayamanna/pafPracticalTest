$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validatePaymentForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
		url : "UserAPI",  
		type : type,  
		data : $("#formUser").serialize(),  
		dataType : "text",  
		complete : function(response, status)  
		{   
			onUserSaveComplete(response.responseText, status);  
		} 
	}); 
});


function onUserSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divUserGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidPaymentIDSave").val("");  
	$("#formUser")[0].reset(); 
}


//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidUserIDSave").val($(this).data("id"));     
	$("#cusId").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#telNo").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#date").val($(this).closest("tr").find('td:eq(2)').text());  
	$("#amount").val($(this).closest("tr").find('td:eq(3)').text());
	$("#cardNo").val($(this).closest("tr").find('td:eq(4)').text());     
	$("#postalNo").val($(this).closest("tr").find('td:eq(5)').text());  
	
});


//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "UserAPI",
		type : "DELETE",
		data : "Id=" + $(this).data("id"),
		dataType : "text",
		complete : function(response, status)
		{
			onUserDeleteComplete(response.responseText, status);   
		}
	}); 
});


function onUserDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divUserGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}

//CLIENT-MODEL================================================================
function validateUserForm()
{
	// 	USER ID
	if ($("#UserId").val().trim() == "")
	{
		return "Insert User Code.";
	}
	
	// NAME
	if ($("#name").val().trim() == "")
	{
		return "Insert User first Name.";
	}
	
	// LAST NAME
	if ($("#lname").val().trim() == "")
	{
		return "Insert user lname.";
	}
	
	// EMAIL-------------------------------
	if ($("#email").val().trim() == "")
	{
		return "Insert your email address.";
	}
	
}

