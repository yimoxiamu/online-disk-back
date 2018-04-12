
function delCookie()
{
    var name="name";
    var exp = new Date();
    exp.setTime(exp.getTime() - 100000);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

// $(function () {
//     $('.user').click(function () {
//         $.ajax({
//             type:'get',
//             url:'http://localhost:8080/getUsers',
//             success:function(data){
//                 if(data=="ok"){
//                 }else{
//                     alert(data);
//                 }
//             }
//         })
//     })
// })