    const socket = new SockJS('/ws'); // Connect to the WebSocket endpoint
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, (frame) => {
        stompClient.subscribe('/topic/jobSearchResults', (response) => {
            // Handle incoming messages
            const responseContent = JSON.parse(response.body).responseContent;
            displayMessage(responseContent);
        });
    });

    function sendMessage() {
        const message = document.getElementById('message').value;
        stompClient.send('/app/JobSearch', {}, JSON.stringify({ 'message': message }));
    }

    function sendConsoleMessage(input) {
        var message = input;
        stompClient.send('/app/JobSearch', {}, JSON.stringify({ 'message': message }));
    }

    function displayMessage(message) {
        const messagesDiv = document.getElementById('messages');
        const messageDiv = document.createElement('div');
        messageDiv.textContent = message;
        messagesDiv.appendChild(messageDiv);
    }


        function displayMessage1() {
    console.log("***testing 123***")
    }

