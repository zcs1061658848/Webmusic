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
});

</script>

  <%List<TArea> arealist=(List<TArea>)request.getAttribute("areaList");
  List<TSinger> singerlist=(List<TSinger>)request.getAttribute("singerList");  
   %>
   <div id="area">
   <a class="href" id="music/list/library.html">全部</a>
   <% for(TArea area : arealist){%>
   		<a class="href" id="music/list/library.html?areaid=<%=area.getAreaid() %>&sex=1"> <%=area.getAreaname() %>男歌手
   		</a>&nbsp;
   		<a class="href" id="music/list/library.html?areaid=<%=area.getAreaid() %>&sex=2"> <%=area.getAreaname() %>女歌手
   		</a>&nbsp;
  	<% } %>
   </div>
   <div>
   <a class="href" id="music/list/library.html?intial=a">A</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=b">B</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=c">C</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=d">D</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=e">E</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=f">F</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=g">G</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=h">H</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=i">I</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=j">J</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=k">K</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=l">L</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=m">M</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=n">N</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=o">O</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=p">P</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=q">Q</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=r">R</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=s">S</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=t">T</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=u">U</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=v">V</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=w">W</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=x">X</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=y">Y</a>&nbsp;
   <a class="href" id="music/list/library.html?intial=z">Z</a>&nbsp;
   </div>
   <div> 
   <%if(singerlist.isEmpty()) {
   %>
   	<div id="nosingermsg">没有歌手信息</div>
   <%} else{%>
   <table>
   <tr>
   <th></th>
   <th>姓名</th>
   </tr>
   <% for(TSinger singer : singerlist){%>
   		<tr><td style="width:80%;height:50px;"><img src="http://<%=InetAddress.getLocalHost().getHostAddress() %>:8080/picture/<%=singer.getPicture()  %>"></td>
   		<td><a class="href" id="music/getmusicbysinger.html?singerid=<%=singer.getSingerid() %>"> <%=singer.getSingername() %>
   		</a>&nbsp;</td>
   		</tr>
  	<% }%>
	</table>
	
	<%String selectWay;
		Integer areaid =(Integer) request.getAttribute("areaid"); 
		if(areaid == null){
			selectWay="intial="+request.getAttribute("intial");
		}else{
			selectWay="areaid="+areaid+"&sex="+request.getAttribute("sex");
		}
		Integer pagecount=(Integer)request.getAttribute("pagecount");
		Integer currentpage=(Integer)request.getAttribute("currentpage");
	 %>
	
	<div>
		<a class="href" id="music/list/library.html?page=1&<%=selectWay %>"> 首页  </a>
		<%
			if(pagecount<=5){
				for(int i=1;i<=pagecount;i++){
					if(i==currentpage){
					%><a><%=i %></a><%
					}else{
					%>
					<a class="href" id="music/list/library.html?page=<%=i %>&<%=selectWay %>" > <%=i %>  </a>
					
					<%
					}
				}
			
			
			}
			else if(currentpage==1){%>
			<a > 1</a>
			<% for(int i=1;i<4;i++){
				
				%>
					<a class="href" id="music/list/library.html?page=<%=i+currentpage %>&<%=selectWay %>" > <%=i+currentpage %>  </a>
				<% 
			} %>			<%
			}else if(currentpage==2){
			%>
			<a class="href" id="music/list/library.html?page=1&<%=selectWay %>" > 1  </a>
			<a> 2 </a>
			<% for(int i=1;i<3;i++){
				
				%>
					<a class="href" id="music/list/library.html?page=<%=i+currentpage %>&<%=selectWay %>" > <%=i+currentpage %>  </a>
				<% 
				}
			%>	
			<%
			}else if(currentpage==pagecount){
			%>
			<%
				for(int i=4;i>0;i++){
					%>
					<a class="href" id="music/list/library.html?page=<%=currentpage-i %>&<%=selectWay %>" > <%=currentpage-i %>  </a>
					<%
				}
			
			
			 %>
			<a><%=pagecount %></a>
			
			<%
				
			}else if(currentpage==pagecount-1){
			%>
			<% for(int i=3;i>0;i++){
				%>
					<a class="href" id="music/list/library.html?page=<%=currentpage-i %>&<%=selectWay %>" > <%=currentpage-i %>  </a>
				<% 
			} %>	
			<a> <%=currentpage %>  </a>
			<a class="href" id="music/list/library.html?page=<%=pagecount %>&<%=selectWay %>" > <%=pagecount %>  </a>
			<%
			}else{
			%>
			<%
			}
		 %>
		<a class="href" id="music/list/library.html?page=<%=pagecount %>&<%=selectWay %>" > 尾页  </a>
	 <%}%>
	</div>
	
	
	</div>