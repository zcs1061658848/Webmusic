<%@ page language="java" import="java.util.*,ntu.music.pojo.*,java.net.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script type="text/javascript">
$(function(){
	$(".href").each(function(){
		$(this).click(function(){
		var current_tab = $('#tt').tabs('getSelected');
	 		$('#tt').tabs('update',{
     			tab:current_tab,
     			options : {
        		href:$(this).attr("id")
    			}
			});
		});
	});
	$(".playmusic").each(function(){
			var id=$(this).attr("id");
			var name=$(this).attr("name");
			var hrefAndId=id.split(",");
			var href=hrefAndId[0];
			var musicid=hrefAndId[1];
		$(this).click(function(){
			ClearDATA();
			DATA(name,href,musicid);
			$("#list").trigger("change");
		});
	
	});
});

</script>



<div>
	
<img align="right" class='href' id="music/list/library.html" style="width:30px;height:30px;" src="img/back.jpg">

</div>


	<%List<TMusic> musiclist=(List<TMusic>)request.getAttribute("musiclist"); %>
  	<table style="width:80%;">
  		<tr align="left"><th>歌曲名</th>
  		<th>专辑</th>
  		<th></th>
  		</tr>
  		<% for(TMusic music:musiclist) {  		%>
  		<tr>
			  <td> <a  class='playmusic' name="<%=music.getMusicname() %>"  id="http://<%=InetAddress.getLocalHost().getHostAddress() %>:8080/music/<%=music.getMusic()%>,<%=music.getMusicid() %>"> <%=music.getMusicname() %> </a>  </td>
			  <td><a  class='href' id="music/getMusicByAlbum?albumid=<%=music.getTAlbum().getAlbumid() %>&singerid="><%=request.getAttribute("singerid") %> <%=music.getTAlbum().getAlbumname() %></a> </td>
			  <td> <img style="width:20px;height:20px;" src="img/play.jpg"> &nbsp;&nbsp;<img src="img/add.jpg" style="width:20px;height:20px;">&nbsp; &nbsp;<img style="width:20px;height:20px;" src="img/download.jpg">  </td>
		</tr>
  		<% }%>
  	</table>
  

