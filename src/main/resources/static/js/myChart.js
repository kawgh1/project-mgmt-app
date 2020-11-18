let chartDataString = decodeHtml(chartData);
let chartJsonArray = JSON.parse(chartDataString);

let arrayLength = chartJsonArray.length;
let numericData = [];
let labelData = [];

for(let i=0; i<arrayLength; i++) {
    // populate data arrays for our JSChart to read from the Json Strings of returned database query data
    numericData[i] = chartJsonArray[i].value; // numericData = [1,2,1]
    labelData[i] = chartJsonArray[i].label;     // labelData = ["COMPLETED", "INPROGRESS", "NOTSTARTED"]
}

// pie chart at www.chartjs.org
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    // data from our dataset
    data: {
        labels: labelData,
        datasets: [{
            label: 'My First Dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            borderColor: 'whitesmoke',
            data: numericData
        }]
    },

    //configuration options go here
    options: {
        title: {
            display: true,
            text: 'Project Status'
        }
    }
});



// Decode raw HTML from backend into readable JSON in the HTML template
// String "[{"value": 1, "label": "COMPLETED"}, {"value": 2, "label": "INPROGRESS"}, {"value": 1, "label": "NOTSTARTED"}]"
function decodeHtml(html){
    let txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}


// example for pie chart at www.chartjs.org
// new Chart(document.getElementById("myPieChart"), {
//     type: 'pie',
//     // data from our dataset
//     data: {
//         labels: ['January', 'February', 'March'],
//         datasets: [{
//             label: 'My First Dataset',
//             backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
//             borderColor: 'rgb(255, 99, 132)',
//             data: [5, 10, 15]
//         }]
//     },
//
//     //configuration options go here
//     options: {}
// });