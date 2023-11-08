// if (!window.stompClient) {
//     const socket = new SockJS('/ws'); 
//     window.stompClient = Stomp.over(socket);

//     stompClient.connect({}, (frame) => {
//         console.log("Connection Sucessful")
//     });
// }


const socket = new SockJS('/ws'); // Connect to the WebSocket endpoint
const stompClient = Stomp.over(socket);

stompClient.connect({}, (frame) => {
    stompClient.subscribe('/topic/jobSearchResults', (response) => {
        // Handle incoming messages
        const responseContent = JSON.parse(response.body);
        displayMessage(responseContent);
    });
});

// function sendMessage() {
//     const message = document.getElementById('message').value;
//     stompClient.send('/app/JobSearch', {}, JSON.stringify({ 'message': message }));
// }




function sendMessage() {
    const message = document.getElementById('message').value;

    // Send a WebSocket message
    stompClient.send('/app/JobSearch', {}, JSON.stringify({ 'message': message }));

    const existingScripts = document.querySelectorAll('script[data-remove]');
    existingScripts.forEach((script) => {
        script.remove();
    });

    // Fetch the content of results.html
    fetch('results.html')
        .then((response) => response.text())
        .then((html) => {
            // Replace the entire body content of index.html with the content of results.html
            document.body.innerHTML = html;

            // You'll need to ensure that JavaScript in results.html is self-contained and functions properly when loaded this way.
        });

    const initialCSS = document.querySelector('link[href*="indexStyles.css"]');
    if (initialCSS) {
        initialCSS.disabled = true;
    }

    const newScript1 = document.createElement('script');
    newScript1.src = 'js/resultsScripts.js'; // Replace with the path to your script
    document.body.appendChild(newScript1);
}




function displayMessage(message) {
    const resultsDiv = document.getElementById('jobListings');

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
                <div class="date-employer-container">
                    <p><strong>Posted on:</strong> ${job.date}</p>
                    <p><strong>Company:</strong> ${job.employerName}</p>
                </div>
                <p><strong>Applicants:</strong> ${job.applications}</p>
                <div class="location-salary-container">
                    <p><strong>Location:</strong> ${job.locationName}</p>
                    <p><strong>Salary:</strong> ${job.currency} ${job.minimumSalary} - ${job.maximumSalary}</p>
                </div>
                <p><strong>Job Description:</strong> ${job.jobDescription}</p>
                <a href="${job.jobUrl}" target="_blank">Read More</a>
            `;

            jobDiv.style.fontFamily = "'Source Sans Pro', sans-serif";
            resultsDiv.appendChild(jobDiv);
        });
    } else {
        // Handle the case where the response is not an array of job listings
        resultsDiv.innerHTML = 'No job listings found.';
    }
}


