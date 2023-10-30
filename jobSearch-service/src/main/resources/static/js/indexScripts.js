    const socket = new SockJS('/ws'); // Connect to the WebSocket endpoint
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, (frame) => {
        stompClient.subscribe('/topic/jobSearchResults', (response) => {
            // Handle incoming messages
            const responseContent = JSON.parse(response.body);
            displayMessage(responseContent);
        });
    });

    function sendMessage() {
        const message = document.getElementById('message').value;
        stompClient.send('/app/JobSearch', {}, JSON.stringify({ 'message': message }));
    }

function displayMessage(message) {
    const resultsDiv = document.getElementById('messages');

    // Clear any previous results
    resultsDiv.innerHTML = '';

    // Check if the message is an array
    if (Array.isArray(message)) {
        // Iterate through the job listings and create HTML elements for each listing
        message.forEach((job) => {
            const jobDiv = document.createElement('div');
            jobDiv.classList.add('job-listing');

            jobDiv.innerHTML = `
                <h3>${job.jobTitle}</h3>
                <p><strong>Employer:</strong> ${job.employerName}</p>
                <p><strong>Location:</strong> ${job.locationName}</p>
                <p><strong>Salary:</strong> ${job.currency} ${job.minimumSalary} - ${job.maximumSalary}</p>
                <p><strong>Job Description:</strong> ${job.jobDescription}</p>
                <p><strong>Applications:</strong> ${job.applications}</p>
                <a href="${job.jobUrl}" target="_blank">Read More</a>
                <hr>
            `;

            resultsDiv.appendChild(jobDiv);
        });
    } else {
        // Handle the case where the response is not an array of job listings
        resultsDiv.innerHTML = 'No job listings found.';
    }
}


