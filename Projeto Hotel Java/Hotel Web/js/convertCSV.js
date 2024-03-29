$(document).ready(() => {

    $('#buttonDownload').click('submit', (e) => {

        download();
        e.preventDefault();

    });
});

function convertToCSV(objArray) {
    var array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;
    var str = '';

    for (var i = 0; i < array.length; i++) {
        var line = '';
        for (var index in array[i]) {
            if (line != '') line += ';'
            line += array[i][index];
        }

        str += line + '\r\n';
    }

    return str;
}

function exportCSVFile(headers, items, fileTitle) {
    if (headers) {
        items.unshift(headers);
    }

    // Convert Object to JSON
    var jsonObject = JSON.stringify(items);

    var csv = this.convertToCSV(jsonObject);

    var exportedFilename = fileTitle + '.csv' || '.csv';

    var blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' });
    if (navigator.msSaveBlob) {
        navigator.msSaveBlob(blob, exportedFilename);
    } else {
        var link = document.createElement("a");
        if (link.download !== undefined) {

            var url = URL.createObjectURL(blob);
            link.setAttribute("href", url);
            link.setAttribute("download", exportedFilename);
            link.style.visibility = 'hidden';
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }
    }
}

function download() {
    var headers = {
       //AQUI VAI O HEADER
    };
    let page = sessionStorage.getItem("pagina");
    let findText = document.getElementById("title");
    let url = "";//URL DA APLICACAO 

    axios.get(url).then((response) => {
        var itemsNotFormatted = [];
        itemsNotFormatted = response.data.Search;

        var itemsFormatted = [];

        // format the data
        itemsNotFormatted.forEach((item) => {
            itemsFormatted.push({
               // Name1: item.Title,
               // Name2: item.Year,
               // Name3: item.Type,
            });
        });

        var fileTitle = 'CineFlow Archive';

        exportCSVFile(headers, itemsFormatted, fileTitle);

    })
        .catch(err => { throw err });
}