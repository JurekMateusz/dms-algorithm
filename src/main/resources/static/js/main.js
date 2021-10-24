let mapOfTasksJson = new Map();

$(document).ready(function () {
  var max_fields = 20; //maximum input boxes allowed
  var wrapper = $(".input_fields_wrap"); //Fields wrapper
  var add_button = $(".add_field_button"); //Add button ID

  var send_button = $(".js-button");
  var x = 2; //initlal text box count

  var taskNumber = 1;
  $(send_button).click(function (event) {
    event.preventDefault();   //block send function in button form

  })
  $(add_button).click(function (e) { //on add input button click
    e.preventDefault();
    if (x < max_fields) {
      //while(existNumberText){
      //  x++/taskNumber++
      // }
      var period = $("#period" + taskNumber).val();
      var completion = $("#completion" + taskNumber).val();
      var deadline = $("#deadline" + taskNumber).val();
      if (period.length === 0 || completion.length === 0 || deadline.length
          === 0) {
        return;
      }
      mapOfTasksJson.set(taskNumber, {
        'taskName': "T"+taskNumber,
        'period': period,
        'completionTime': completion,
        'deadline': deadline
      });

      x++;
      taskNumber++;

      $(wrapper).append(
          '<p><div class="section' + taskNumber + '">'
          + '<input class="form__input" value="T'+taskNumber+'">'
          + '<input id="period'+ taskNumber+'" class="form__input" placeholder="P">'
          + '<input id="completion'+ taskNumber+'" class="form__input" placeholder="C">'
          + '<input id="deadline'+ taskNumber+'" class="form__input" placeholder="D">'
          + '<div class="input-group-append">'
          + '<button class="remove_field removeButton" type="button">Remove</button>'
          + '</div>'
          + '</div></p>'); //add input box
    }
  });
  $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
    e.preventDefault();
    $(this).parent('div').parent('div').remove();
    taskNumber--;
    x--;
  })

});

let buttonSend = document.querySelector(".js-button");
const getResult = async () => {
  document.getElementById('show-results').style.display = 'none';

  jsonStr = JSON.stringify(mapToArray(mapOfTasksJson));
  const response = await fetch('http://localhost:8080/dms/', {
    method: 'POST',
    body: jsonStr,
    headers: {
      'Content-Type': 'application/json'
    }
  });
  const myJson = await response.json();
  showChart(myJson);
}

function mapToArray(map) {
  let array = [];
  map.forEach(x => array.push(x));
  return array;
}