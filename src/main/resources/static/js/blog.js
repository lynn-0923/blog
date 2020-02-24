function post() {
    var questionId=$("#comment_id").val();
    var content = $("#comment_content").val();
    if(!content){
        alert("Input can not be empty");
        return ;
    }

    $.ajax({
        type:"POST",
        contentType:"application/json",
        url:"/comment",
        data:JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1
        }),
        success:function(response){
            if(response.code == 200){
               window.location.reload();
            }else {
              if(response.code == 2003){
                  var isAccepted = confirm(response.message);
                  if(isAccepted){
                      window.open("https://github.com/login/oauth/authorize?client_id=6557c4ae0b77d9ba7586&redirect_uri=http://localhost:1106/callback&scope=user&state=1");
                      window.localStorage.setItem("closable","true");
                  }
              }else {
                  alert(response.message);
              }
            }
        },
        dataType:"json"
    });
}