$(document).ready(function() {
  var max_fields      = 999; //maximum input boxes allowed
  var wrapper   		= $(".input_fields_wrap"); //Fields wrapper
  var add_button      = $(".add_field_button"); //Add button ID
  var send_button     = $(".js-button");

  var x = 1; //initlal text box count
  var i = 0;

  $(send_button).click(function(event){
    event.preventDefault();   //block send function in button form
  })

  $(add_button).click(function(e){ //on add input button click
    e.preventDefault();
    if(x < max_fields){ //max input box allowed
      x++; //text box increment
      i++;
      $(wrapper).append('<p><div class="section__new"><input class="form__input" value="T'+i+'"><input class="form__input" placeholder="P"><input class="form__input" placeholder="C"><input class="form__input" placeholder="D"><div class="input-group-append"><button class="remove_field removeButton" type="button">Remove</button></div></div></p>'); //add input box

    }
  });
  $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
    e.preventDefault(); $(this).parent('div').parent('div').remove(); i--;x--;
  })
});

let buttonSend = document.querySelector(".js-button");
