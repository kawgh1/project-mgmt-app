// for pie chart at www.chartjs.org
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    // data from our dataset
    data: {
        labels: ['January', 'February', 'March'],
        datasets: [{
            label: 'My First Dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            borderColor: 'rgb(255, 99, 132)',
            data: [5, 10, 15]
        }]
    },

    //configuration options go here
    options: {}
});