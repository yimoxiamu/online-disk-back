
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


function openModal() {
    parent.document.getElementById('my-alert').modal('open');
}

$(function () {
    $('.one-name').click(function () {
        var name=$(this).html();
        $.ajax({type:"post",
        url:'http://localhost:8080/getUser',
        dataType:"json",
        data:{"name":name},
        success:function (data) {
            $('.name-one').val(data.name);
            $('.pass-one').val(data.pass);
            $('.email-one').val(data.email);
            $('.size-one').val(data.size);
            $('.use-one').val(data.use);
            $('.id-one').val(data.id);
            $('.msg-one').val(data.msg);
            if(data.staus==0){
                // $("input:radio[value='0']").attr('checked','true');
                $("input:radio[name='staus_update']").eq(0).attr('checked','0');
            }else if(data.staus==1){
                // $("input:radio[value='1']").attr('checked','true');
                $("input:radio[name='staus_update']").eq(1).attr('checked','1');}
            if(data.vip==1){
                $("input:radio[name='vip_update']").eq(0).attr('checked',true);
            }else if(data.vip==0){
                $("input:radio[name='vip_update']").eq(1).attr('checked','0');
            }
        }})
    })

    $('.pageNum').click(function () {
        var pageNum=$(this).attr("value");
        $('.hidden-text').val(pageNum);
        $('#hidden-form').submit();
    })

    $('#pullMsg').click(function () {
        var msg=$('.msg').val();
        $('.msg-text').val(msg);
        $('.form-msg').submit();
    })
})


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