var setting = function() {

      //For gender selection checkbox during user registeration
      $("input:checkbox").on('click', function() {
        var $box = $(this);
        if ($box.is(":checked")) {
          var group = "input:checkbox[name='" + $box.attr("name") + "']";
          $(group).prop("checked", false);
          $box.prop("checked", true);
        } else {
          $box.prop("checked", false);
        }
      });

      //For datepicker at the user registeration
      $('.datepicker').pickadate({
          selectMonths: true, // Creates a dropdown to control month
          selectYears: 100,   // Creates a dropdown of 15 years to control year
        });

      $(document).ready(function() {
          $('select').material_select();

          //For character counts during input fields
          $('input#reg_username,#first_name,#last_name,#reg_password,#confirm_password,#email, textarea#textarea1').characterCounter();
        
          //For tooltip
          $('.tooltipped').tooltip({delay: 50});

          //For tabs
           $('ul.tabs').tabs();
      });

       $('.dropdown-button').dropdown({
        inDuration: 300,
        outDuration: 225,
        constrainWidth: true, // Change width of dropdown to that of the activator
        hover: true, // Activate on hover
        gutter: 0, // Spacing from edge
        belowOrigin: true, // Displays dropdown below the button
        alignment: 'left', // Displays dropdown with edge aligned to the left of button
        stopPropagation: false // Stops event propagation
      });

       $('.profile-button').dropdown({
        inDuration: 300,
        outDuration: 225,
        constrainWidth: false, // Does not change width of dropdown to that of the activator
        hover: true, // Activate on hover
        gutter: 0, // Spacing from edge
        belowOrigin: true, // Displays dropdown below the button
        alignment: 'left', // Displays dropdown with edge aligned to the left of button
        stopPropagation: false // Stops event propagation
       });

       $('.news-button').dropdown({
        inDuration: 300,
        outDuration: 225,
        constrainWidth: false, // Does not change width of dropdown to that of the activator
        hover: true, // Activate on hover
        gutter: 0, // Spacing from edge
        belowOrigin: true, // Displays dropdown below the button
        alignment: 'left', // Displays dropdown with edge aligned to the left of button
        stopPropagation: false // Stops event propagation
       });

       // Initialize collapse button
      $(".side-collapse").sideNav();
      // Initialize collapsible (uncomment the line below if you use the dropdown variation)
      //$('.collapsible').collapsible();
        
}

setting();
	