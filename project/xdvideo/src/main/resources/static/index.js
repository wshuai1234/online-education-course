
var host = "http://127.0.0.1:8081"
var global_login_url = ""  //gloabl scan login

//order
function save_order(id){
	var token =$.cookie("token");
	if(!token || token == ""){
		//go to login
		window.location.href=global_login_url;

	}
	//order interface
	var url = host+"/user/api/v1/order/add?token="+token + "&video_id="+id;
	$("#pay_img").attr("src",url);

}


$(function(){


	//get video list
	function get_list(){

			$.ajax({
				type:'get',
				url: host+"/api/v1/video/page?size=30&page1",
				dataType:'json',
				success:function(res){
						var data = res.data;
						
						for(var i=0; i<data.length; i++){
							var video = data[i];
							var price = video.price/100;

							var template = "<div class='col-sm-6 col-md-3'><div class='thumbnail'>"+
				        	"<img src='"+video.coverImg+"'alt='通用的占位符缩略图'>"+
				        	"<div class='caption'><h3>"+video.title+"</h3><p>price:"+price+"dollar</p>"+
				        	"<p><a href='' onclick='save_order("+video.id+")' data-toggle='modal' data-target='#myModal' class='btn btn-primary' role='button'>buy now</a></p></div></div></div>"
				        	$(".row").append(template);
						}
				}
	})}


	//get wechat scan address
	function get_wechat_login(){
		//console.log(111)
		//get current page url
		var current_page = window.location.href;
			$.ajax({
				type:'get',
				url: host+"/api/v1/wechat/login_url?access_page="+current_page,
				dataType:'json',
				success:function(res){
						//console.info(res.data)
						$("#login").attr("href",res.data);
						global_login_url = res.data;
				}
		})

	}

	//get url param
	function get_params(){
		var url = window.location.search;//get string after ?
		var obj = new Object();
		if(url.indexOf("?")!=-1){
			var str = url.substr(1);
			//console.log(str);
			strs = str.split("&")
			for(var i=0; i<strs.length; i++){
				obj[strs[i].split("=")[0]] = decodeURI( strs[i].split("=")[1]);
			}

		}
//		console.log(obj)
		return obj;
	}


	//set avatar and nickname
	function set_user_info(){
		var user_info = get_params();
		var head_img  = $.cookie('head_img')
		var name = $.cookie('name')

		if(JSON.stringify(user_info) != '{}'){
			//object not null
			var name = user_info['name'];
			var head_img = user_info['head_img']
			var token = user_info['token']
			console.log(name)
			console.log(head_img)

			$("#login").html(name)
			$("#head_img").attr("src",head_img);
			$.cookie('token',token,{expires:7,path:'/'})
			$.cookie('head_img',head_img,{expires:7,path:'/'})
			$.cookie('name',name,{expires:7,path:'/'})

		} else if(name && name != ""){
			
				$("#login").html(name)
				$("#head_img").attr("src",head_img);
		}

	}




	get_list();
	get_wechat_login();
	get_params();
	set_user_info();

})