const url = "http://localhost:8080/tasks/user-tasks/1}";

function hideLoader(){
    document.getElementById("loading").style.display="none";
}

function show (task){
    let tab = `<thead>
        <th scope="col">#</th>    
        <th scope="col">Description</th>
        <th scope="col">Username</th>
        <th scope="col">User Id</th>
    </thead>
    `;
}

for (let item of tasks) {
    tab += `
        <tr>
            <td scope="row>${task.id}</td>
            <td>${task.description}</td>
            <td>${task.usdername}</td>
            <td>${task.user.id}</td>
        </tr>
    `
    document.getElementById("tasks").innerHTML = tab;
}

async function getApi(url){
    const response = await fetch(url, {method:"GET"});
    var data = await response.json();
    console.log(data);
    if(response){
        hideLoader();
        show(data);
    }
}

getApi(url);

