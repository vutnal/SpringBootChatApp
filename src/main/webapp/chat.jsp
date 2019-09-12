<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Chat WebSocket</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
        <script type="text/javascript">
            var stompClient = null;
             
            function setConnected(connected) {
                document.getElementById('connect').disabled = connected;
                document.getElementById('disconnect').disabled = !connected;
                document.getElementById('conversationDiv').style.visibility 
                  = connected ? 'visible' : 'hidden';
                document.getElementById('response').innerHTML = '';
            }
             
            function connect() {
                var socket = new SockJS('/secured/room');
                var sessionId = "";
                stompClient = Stomp.over(socket);  
                stompClient.connect({}, function(frame) {
                	var url = stompClient.ws._transport.url;
                    url = url.replace(
                      "ws://localhost:8080/secured/room/",  "");
                    url = url.replace("/websocket", "");
                    url = url.replace(/^[0-9]+\//, "");
                    console.log("Your current session is: " + url);
                    sessionId = url;
                    setConnected(true);
                    
                    stompClient.subscribe('/user/'+document.getElementById('from').value+'/queue/specific-user' 
                  		  , function(messageOutput) {
                  	  //alert(messageOutput);
                      showMessageOutput(JSON.parse(messageOutput.body));
                    });
                    
                    
                    
                });
            }
             
            function disconnect() {
                if(stompClient != null) {
                    stompClient.disconnect();
                }
                setConnected(false);
                console.log("Disconnected");
            }
             
            function sendMessage() {
                var from = document.getElementById('from').value;
                var to = document.getElementById('to').value;
                var text = document.getElementById('text').value;
                
                stompClient.send("/secured/room", {}, 
                  JSON.stringify({'from':from, 'to': to, 'text':text}));
            }
             
            function showMessageOutput(messageOutput) {
                var response = document.getElementById('response');
                var p = document.createElement('p');
                p.style.wordWrap = 'break-word';
                p.appendChild(document.createTextNode(messageOutput.from + ": " 
                  + messageOutput.text + " (" + messageOutput.time + ")"));
                response.appendChild(p);
            }
        </script>
    </head>
    <body onload="disconnect()">
        <div>
            <div>
                <h2>Welcome ${pageContext.request.userPrincipal.name}</h2>
                <input type="hidden" id="from" value="${pageContext.request.userPrincipal.name}">
            </div>
            <br />
            <div>
                <button id="connect" onclick="connect();">Connect</button>
                <button id="disconnect" disabled="disabled" onclick="disconnect();">
                    Disconnect
                </button>
            </div>
            <br />
            <div id="conversationDiv">
	            <div>
	                <input type="text" id="to" placeholder="Send To"/>
	            </div>
                <input type="text" id="text" placeholder="Write a message..."/>
                <button id="sendMessage" onclick="sendMessage();">Send</button>
                <p id="response"></p>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    </body>
</html>