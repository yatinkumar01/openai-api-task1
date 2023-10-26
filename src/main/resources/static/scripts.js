function ConvertCode() {
    let selectElement = document.getElementById("lang");
    let inputElement = document.getElementById("myInput");
    const url = "http://localhost:8088/chat/convert";
    let data = {
        prompt: inputElement.value,
        language: selectElement.value
    }

    console.log(data)

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    })
        .then((data) => {
            return data.json().then(parsedData => {
                appendData(parsedData.result)
            });
        })
        .catch((error) => {
            console.error("There was a problem with the fetch operation: " + error.message);
        });
}


function DebugCode() {
    let inputElement = document.getElementById("myInput");
    const url = "http://localhost:8088/chat/debug";
    let data = {
        prompt: inputElement.value,
    }

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    })
        .then((data) => {
            return data.json().then(parsedData => {
                appendData(parsedData.result)
            });
        })
        .catch((error) => {
            console.error("There was a problem with the fetch operation: " + error.message);
        });
}

function getQualityCode() {
    let inputElement = document.getElementById("myInput");
    const url = "http://localhost:8088/chat/qualityCheck";
    let data = {
        prompt: inputElement.value,
    }

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    })
        .then((data) => {
            return data.json().then(parsedData => {
                appendData(parsedData.result)
            });
        })
        .catch((error) => {
            console.error("There was a problem with the fetch operation: " + error.message);
        });
}

function appendData(result) {
    let div = document.createElement("div")
    div.append(result)
    document.getElementById("right").append(div)
}