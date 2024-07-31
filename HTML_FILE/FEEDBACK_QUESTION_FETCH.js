function parseCSV(csvData) {
    const rows = csvData.split('\n');
    const result = rows.map(row => row.split(','));
    return result;
}

// Fetch the CSV file
fetch('FEEDBACK_QUESTIONS.csv')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text();
    })
    .then(data => {
        const parsedData = parseCSV(data);
        const table = document.createElement('table');
        parsedData.forEach(rowData => {
            const row = document.createElement('tr');
            rowData.forEach(cellData => {
                const cell = document.createElement('td');
                cell.textContent = cellData;
                row.appendChild(cell);
            });
            table.appendChild(row);
        });
        document.getElementById('csvData').appendChild(table);
    })
    .catch(error => {
        console.error('Error fetching or parsing CSV:', error);
    });