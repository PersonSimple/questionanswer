$(document).ready(function () {

$("#teacher-form").submit(function (event) {
         
        //stop submit the form event. Do this manually using ajax post function
        event.preventDefault();

        var teacherForm = {}
//        teacherForm["username"] = $("#username").val();
//        teacherForm["password"] = $("#password").val();

//       $("#btn-login").prop("disabled", true);
        
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/super/admin/performance",
            data: JSON.stringify(teacherForm),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                //$("#btn-login").prop("disabled", false);

            },
            error: function (e) {

                var json = "<h4>Ajax Response Error</h4><pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#btn-login").prop("disabled", false);

            }
        });
        
    });

});