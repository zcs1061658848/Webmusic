<%@ page language="java" import="ntu.music.pojo.TUser"
	pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'main.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/52player.css" type="text/css" />
	
	
<script type="text/javascript"
	src="js/jquery-easyui-1.3.6/jquery-2.1.0.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.3.6/jquery.easyui.min.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"
	charset="UTF-8"></script>
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.3.6/themes/default/easyui.css" charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.3.6/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/main.css">


<script type="text/javascript">

	var isadd=false;
    $(
       function(){
       function loadTree(){
       	 $('#tree').tree({
          url:'music/getTree.html',
          onClick:function(node){
          		if(node.text == "我的音乐"){
          		}
          		else{
          			addTab(node.text);
          		}
    	 		}
    		 });
    	 }
    	 
        loadTree();
       
     ManagerUserListDialog = $("#managerUserList").dialog({
     		title:"管理播放列表",
     		closed: true,
			closable : true,
			collapsible:true,
			width:500,
			height:350,
			modal : true,
		    onClose:function(){
  	  	 		loadTree();
  		  	},
			buttons : [ 
			
			{
				text : '删除',
				handler : function() {
					var obj=document.getElementsByName('userlistcheckbox'); 
					var s='';
					for(var i=0; i<obj.length; i++){
					if(obj[i].checked)
						 s+=obj[i].value+','; //如果选中，将value添加到变量s中
					}
					if(s == ""){
						alert('请选择');
					}else{
						if(confirm("确认删除？")){
						s = s.substring(0,s.length-1);
							$.ajax({
								url:'music/deleteUserList.html',
								data : {"ids":s},
								success:function(r){
									if(r == "success"){
										alert('删除成功');
										loadlist();
									}else{
										alert('删除出错');
									}
								}
							});
						
						}
					}
				}
			},{
				text : '添加',
				handler : function() {
				
				if(!isadd){
					$("#listtable").html($("#listtable").html()+"<tr id='add'><td></td><td><input type='text' id='addtext' length='10'></td><td><input class='l-btn' type='button' id='addsure' value='确认'> &nbsp;<input class='l-btn'  id='addcancel' type='button' value='取消'></td></tr>");
					isadd=true;
					
					$("#addcancel").click(function(){
						$("#add").remove();
							loadlist();
						isadd=false;
					});
					$("#addsure").click(function(){
						$.ajax({
							url:'music/addUserList.html',
							data:{"name":$("#addtext").val()},
							success:function(r){
								if(r == "success"){
									alert("添加成功");
									isadd=false;
									loadlist();
								}else{
									alert("列表名不能重复");
									$("#addtext").focus();
								}
							
							}
						});
					
					});
				}
				}
			}, {
				text : '关闭',
				handler : function() {
					 $("#managerUserList").dialog('close');
				}
			} ]
		});
		
		var isclick = new Array();
		var i=0;
		var j=0;
		function loadlist(){
		 $.ajax({
     	 	url:"music/getUserList.html",
     	 	type:"post",
     	 	success : function(r) {
						$("#userlistMessage").html(r);
						$(".editbutton").each(function(){
							isclick[i]=false;
							i++;
							$(this).click(function(){
							if(!isclick[i]){
								var current=$(this).parent().prev();
								var text=current.html();
     							current.html("<input type='text' id='input"+text+"' value='"+current.text()+"'>");
     							isclick[i]=true;
     							$("#cancel"+text).click(function(){
									current.html(text);
									isclick[i]=false;
								});	
							}
							});
							
							
						});
						$(".surebutton").each(function(){
							var current=$(this).parent().prev();
							var text=current.html();
							j++;
							$("#sure"+text).click(function(){
								var newtext=$("#input"+text).val();
									if(text == newtext){
										current.html(text);
										isclick[j]=false;
									}else{
										$.ajax({
											url:"music/edituserlist.html",
											data:{"name":text,"newname":newtext},
											method:"post",
											success:function(r){
												if(r == "success"){
													alert("修改成功");
													isclick[j]=false;
													loadlist();
												}else{
													alert("列表名称不能重复");
													$("#input"+text).focus();
												}
											}
										});
									}
								});
								});
						
					}
     	 });
		
		}
	
		
    	 $("#eidtUserList").click(function(){
    	 	$("#managerUserList").dialog('open');	
     			loadlist();
     });
     
     function addTab(title){
		//先判断是否存在标题为title的选项卡
		var tab=$('#tt').tabs('exists',title);
		if(tab){
			//若存在，则直接打开
			$('#tt').tabs('select',title);
		}else{
			var href;
			if(title == "音乐库")
				href="library";
			else if(title == "乐库管理")
				href="libraryManager";
			else
				href="list";
			//否则创建
			$('#tt').tabs('add',{
				title:title,
				href:"music/list/"+href+".html?title="+title,
				closable:true
			});
		}
		
	}
       
        /*
	*刷新时间
	*/
	function showTime(){
		var date=new Date();
		$('#timeInfo').html('欢迎你，<%=((TUser) (request.getSession().getAttribute("user")))
					.getUsername()%>&nbsp;&nbsp;&nbsp;&nbsp;'+ date.toLocaleString() + "&nbsp;&nbsp;");
		}
		setInterval(showTime, 1000);

	});
</script>
</head>

<body class="easyui-layout"
	style="border:none;visibility:visible;width: 100%;height: 100%;"
	onload="showTime();">
	<div region="north" style="overflow:hidden;height:60px">
		<div class="header">
			<div class="maintitle" style="top: 12;">音乐系统</div>
			<div style="text-align:right;padding-right: 20px;padding-top: 22px;">
				<span style="color:#ddd" id="timeInfo"></span> <a href="login/layout.html"
					style="color:#ddd;text-decoration:none;">退出</a>
			</div>
		</div>
	</div>
	<div region="south" split="true"
		style="height:50px;background:#efefef;">
			
			
			
			<!--52player代码开始//-->
<div id="player"></div>
<div class="player">
	<div class="player-l bg"></div>
	<div class="player-block bg">
		<div class="playlist">
			<span><p class="bg"></p>列表</span>
			<select class="selectobj" id="list"></select>
			<div class="select"></div>
		</div>
		<div class="seprator bg"></div>
		<div class="controls">
			<a href="#" class="pre mode-bg" title="上一曲"></a>
			<a href="#" class="play mode-bg" title="播放" style="display:none"></a>
			<a href="#" class="pause mode-bg" title="暂停"></a>
			<a href="#" class="next mode-bg" title="下一曲"></a>
			<span style="float:left; margin-left:5px; margin-right:3px; display:inline;">
				<a href="#" class="random-mode random-mode-select mode-bg"  title="随机播放"></a>
				<a href="#" class="order-mode mode-bg"  title="列表循环"></a>
				<a href="#" class="single-mode mode-bg"  title="单曲循环"></a>
			</span>
			<a href="#" class="download bg" id="download" title="下载舞曲">下载</a>
		</div>
		<div class="seprator bg"></div>
		<div class="info">
			<div class="song-name">欢迎</div><div class="time" id="playtime">00:00</div><div class="s" >/</div><div class="time" id="totaltime">00:00</div>
			<div class="tracks bg">
				<div class="download-bar bg">
					<div class="l bg">l</div>
					<div class="r bg">r</div>
				</div>
				<div class="seek-bar bg">
					<div class="l bg"></div>
					<div class="r bg"></div>
					<div class="point bg"></div>
				</div>
			</div>
		</div>
		<div class="seprator bg"></div>
		<div class="volumn">
			<a class="mute mode-bg"></a>
			<a class="unmute mode-bg"></a>
			<div class="line bg">
				<div class="tracks" id="volumn">
					<div class="volumn-bar bg">
						<div class="l bg"></div>
						<div class="r bg"></div>
						<div class="point bg"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="player-r bg"></div>
</div>
<!--52player代码结束//-->
<script language="javascript" type="text/javascript" src="js/f.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.jplayer.min.js"></script>
<script language="javascript" type="text/javascript" src="js/main.js"></script>	
<script type="text/javascript">

DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");
DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");
DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");
DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");
DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");
DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");
DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");
DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");
DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");
DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");
DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");
DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");
DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");
DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");
DATA("My Love","http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3","3");

</script>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
		</div>
	<div region="east" icon="icon-reload" title="相关操作" split="true"
		style="width:100px;"></div>
	<div region="west" split="true" title="导航菜单栏" style="width:180px;">
		<div id="tree"></div>
		<div id="eidtUserList" style="width:100%;text-align:right;">
			<input type="button" class="l-btn " value="管理播放列表">
		</div>
	</div>
	<div region="center" style="background:#eee;">
		<div class="easyui-tabs" fit="true" id="tt">
			<div title="主页">
				<iframe width='100%' height='100%' id='iframe' frameborder='0'
					scrolling='auto' src='index.jsp'></iframe>
			</div>
		</div>
	</div>
	<div id="managerUserList" >
		<div id="userlistMessage"></div>




	</div>
</body>
</html>
