/**
 * Created by KelvinMwegaKiana on 12/09/2018.
 */

var chartOptions = {
    scales: {
        xAxes: [{
            type: 'time',
            distribution: 'linear',
            time: {
                tooltipFormat: "MMM-DD h:mm a",
                displayFormats: {
                    'millisecond': 'h:mm a',
                    'second': 'h:mm a',
                    'minute': 'h:mm a',
                    'hour': 'D MMM hA',
                    'day': 	'MMM DD',
                    'week': 'MMM DD',
                    'month': 'MMM DD',
                    'quarter': 'MMM DD',
                    'year': 'MMM DD'
                }
            }
        }],
        yAxes: [{
            ticks: {
                beginAtZero:false
            }
        }]
    }
};

function showLoading() {
    document.getElementById("loader").style.display = "block";
    document.getElementById("graphDiv").style.display = "none";
}

function showPage() {
    document.getElementById("loader").style.display = "none";
    document.getElementById("graphDiv").style.display = "block";
}


function reqFN(dataToSubmit, url, type){

    return $.ajax({
        url : url,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data : JSON.stringify(dataToSubmit),
        type : type,
        contentType: "application/json",
        dataType : "json",
        cache : false,
        success : function(data){
            //console.log(data);
        },
        error : function(xhr, status, error){
            var err = eval("(" + xhr.responseText + ")");
            console.log(err.Message);
        }
    });

}

function replaceAll(find, replace, str)
{
    while( str.indexOf(find) > -1)
    {
        str = str.replace(find, replace);
    }
    return str;
}

function sprintf(template, values) {
    return template.replace(/%s/g, function() {
        return values.shift();
    });
}

function pinSymbol(color) {
    return {
        path: 'M 0,0 C -2,-20 -10,-22 -10,-30 A 10,10 0 1,1 10,-30 C 10,-22 2,-20 0,0 z',
        fillColor: color,
        fillOpacity: 1,
        strokeColor: '#000',
        strokeWeight: 2,
        scale: 1
    };
}

function base64toHEX(base64) {

    try{
        var raw = atob(base64);

        var HEX = '';

        for ( i = 0; i < raw.length; i++ ) {

            var _hex = raw.charCodeAt(i).toString(16);

            HEX += (_hex.length==2?_hex:'0'+_hex);

        }
        return HEX.toUpperCase();
    } catch (e){
        return "00";
    }

}

var opts = {
    angle: 0, /// The span of the gauge arc
    lineWidth: 0.44, // The line thickness
    radiusScale: 1,
    pointer: {
        length: 0.6, // Relative to gauge radius
        strokeWidth: 0.035 // The thickness
    },
    colorStart: '#6FADCF',   // Colors
    colorStop: '#8FC0DA',    // just experiment with them
    strokeColor: '#E0E0E0',  // to see which ones work best for you
    generateGradient: true,
    highDpiSupport: true,
    staticLabels: {
        font: "12px sans-serif",  // Specifies font
        labels: [10, 20, 30, 40, 50, 60],  // Print labels at these values
        color: "#000000",  // Optional: Label text color
        fractionDigits: 0  // Optional: Numerical precision. 0=round off.
    },
    staticZones: [
        {strokeStyle: "#30B32D", min: 10, max: 25}, // Green
        {strokeStyle: "#FFDD00", min: 25, max: 40}, // Yellow
        {strokeStyle: "#F03E3E", min: 40, max: 60}  // Red
    ],
    renderTicks: {
        divisions: 5,
        divWidth: 1.1,
        divLength: 0.7,
        divColor: '#333333',
        subDivisions: 3,
        subLength: 0.5,
        subWidth: 0.6,
        subColor: '#666666'
    }
};

var humopts = {
    angle: 0, /// The span of the gauge arc
    lineWidth: 0.44, // The line thickness
    radiusScale: 1,
    pointer: {
        length: 0.6, // Relative to gauge radius
        strokeWidth: 0.035 // The thickness
    },
    colorStart: '#6FADCF',   // Colors
    colorStop: '#8FC0DA',    // just experiment with them
    strokeColor: '#E0E0E0',  // to see which ones work best for you
    generateGradient: true,
    highDpiSupport: true,
    staticLabels: {
        font: "12px sans-serif",  // Specifies font
        labels: [0, 20, 40, 60, 80, 100],  // Print labels at these values
        color: "#000000",  // Optional: Label text color
        fractionDigits: 0  // Optional: Numerical precision. 0=round off.
    },
    staticZones: [
        {strokeStyle: "#30B32D", min: 0, max: 60}, // Green
        {strokeStyle: "#FFDD00", min: 60, max: 80}, // Yellow
        {strokeStyle: "#F03E3E", min: 80, max: 100}  // Red
    ],
    renderTicks: {
        divisions: 5,
        divWidth: 1.1,
        divLength: 0.7,
        divColor: '#333333',
        subDivisions: 3,
        subLength: 0.5,
        subWidth: 0.6,
        subColor: '#666666'
    }
};

var lightopts = {
    angle: 0, /// The span of the gauge arc
    lineWidth: 0.44, // The line thickness
    radiusScale: 1,
    pointer: {
        length: 0.6, // Relative to gauge radius
        strokeWidth: 0.035 // The thickness
    },
    colorStart: '#6FADCF',   // Colors
    colorStop: '#8FC0DA',    // just experiment with them
    strokeColor: '#E0E0E0',  // to see which ones work best for you
    generateGradient: true,
    highDpiSupport: true,
    staticLabels: {
        font: "12px sans-serif",  // Specifies font
        labels: [0, 20, 40, 60, 80, 100],  // Print labels at these values
        color: "#000000",  // Optional: Label text color
        fractionDigits: 0  // Optional: Numerical precision. 0=round off.
    },
    staticZones: [
        {strokeStyle: "#30B32D", min: 0, max: 60}, // Green
        {strokeStyle: "#FFDD00", min: 60, max: 80}, // Yellow
        {strokeStyle: "#F03E3E", min: 80, max: 100}  // Red
    ],
    renderTicks: {
        divisions: 5,
        divWidth: 1.1,
        divLength: 0.7,
        divColor: '#333333',
        subDivisions: 3,
        subLength: 0.5,
        subWidth: 0.6,
        subColor: '#666666'
    }
};

var batopts = {
    angle: 0, /// The span of the gauge arc
    lineWidth: 0.44, // The line thickness
    radiusScale: 1,
    pointer: {
        length: 0.6, // Relative to gauge radius
        strokeWidth: 0.035 // The thickness
    },
    colorStart: '#6FADCF',   // Colors
    colorStop: '#8FC0DA',    // just experiment with them
    strokeColor: '#E0E0E0',  // to see which ones work best for you
    generateGradient: true,
    highDpiSupport: true,
    staticLabels: {
        font: "12px sans-serif",  // Specifies font
        labels: [3400, 3600, 3800, 4000, 4200, 4400],  // Print labels at these values
        color: "#000000",  // Optional: Label text color
        fractionDigits: 0  // Optional: Numerical precision. 0=round off.
    },
    staticZones: [
        {strokeStyle: "#30B32D", min: 3900, max: 4400}, // Green
        {strokeStyle: "#FFDD00", min: 3650, max: 3900}, // Yellow
        {strokeStyle: "#F03E3E", min: 3400, max: 3650}  // Red
    ],
    renderTicks: {
        divisions: 5,
        divWidth: 1.1,
        divLength: 0.7,
        divColor: '#333333',
        subDivisions: 3,
        subLength: 0.5,
        subWidth: 0.6,
        subColor: '#666666'
    }
};
























