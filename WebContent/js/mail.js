    $(document).ready(function() {
      scroll = function(event) {
        $('#edit-area').scrollTop(0);
        event.preventDefault();
      }

      $(".scrolltop").click(scroll);

      $('#contact-list-wrapper').BootSideMenu({side:"right"});

      var editor_container = $(".editor-container");
      var mail_list_container = $(".mail-list-container");

      function toggle_main(state) {
        if (state == "editor") {
          mail_list_container.slideUp("fast");
          editor_container.slideDown("fast");
        } else if (state == "maillist") {
          editor_container.slideUp("fast");
          mail_list_container.slideDown("fast");
        } else if (state == "none") {
          editor_container.slideUp("fast");
          mail_list_container.slideUp("fast");
        } else if (state == "init") {
          editor_container.slideUp();
          mail_list_container.slideUp();
        }
      }

      toggle_main("init");

      $(".mailbox-list-item").click(function() {
        toggle_main("maillist")
      });

      $(".send").click(function(event) {
        toggle_main("editor");
        var toggler = $(".toggler");
        if (toggler.parent().attr("data-status") == "opened") {
          toggler.trigger("click");
        }
        event.preventDefault();
        event.stopPropagation();
      });

      function account_list_init() {
        var account_list_wrapper = $(".account-list-container");
        var account_list = $(".account-list");
        var list_item = account_list.find(".account-list-item");

        function no_propagation(event) {
          event.stopPropagation();
        }

        list_item.find(".info-header")
          .hover(function() {
            $(this).find(".button-group").slideDown("fast");
          }, function() {
            $(this).find(".button-group").slideUp("fast", function() {
              $(this).find("#delete").children(".text").text("");
              $(this).find("#edit").show();
            });
          });

        list_item.find(".body").click(no_propagation);

        list_item.map(function() {
          var list_items = $(".account-list").find(".account-list-item");
          var list_item = $(this);
          var body = list_item.find(".body");
          var info_body = body.find(".info-body");
          var list_body = body.find(".list-body");
          var info_header = list_item.find(".info-header");
          var edit_header = list_item.find(".edit-header");

          var delete_button = info_header.find("#delete");
          var edit_button = info_header.find("#edit");

          list_item.find(".button-group").hide();

          function change_account_item_state(state) {
            list_item.state = state;
            if (state == "info") {
              body.slideUp("fast");
              edit_header.slideUp("fast");
              info_header.slideDown("fast");
              
            } else if (state == "edit") {
              info_header.slideUp("fast");
              edit_header.slideDown("fast");
              body.show();
              list_body.slideUp("fast");
              info_body.slideDown("fast");
              account_list_wrapper.scrollTop(list_item.offset());
            } else if (state == "list") {
              info_header.slideDown("fast");
              edit_header.slideUp("fast");
              body.show();
              info_body.slideUp("fast");
              list_body.slideDown("fast");
            }
          }

          list_item.click(function() {
            change_account_item_state(list_item.state == "list" ? "info" : "list");
          })

          change_account_item_state("info", 0);

          edit_button.click(function(event) {
            change_account_item_state("edit");
            event.stopPropagation();
          });

          function blink(element, speed) {
            return element.fadeOut(speed).fadeIn(speed);
          }

          delete_button.click(function(event) {
            if (delete_button.children(".text").text() == "Really?") {
              list_item.slideUp("normal");
            } else {
              delete_button.children(".text").text("Really?");
              edit_button.hide();
              blink(delete_button, "slow");
            }
            event.stopPropagation();
          });

          edit_header.find("#save").click(function() {
            change_account_item_state("info");
          });
          edit_header.find("#cancel").click(function() {
            change_account_item_state("info");
          });
        });
      }
      account_list_init();

      function contact_list_init() {
        var account_list_wrapper = $(".contact-list-container");
        var account_list = $(".contact-list");
        var list_item = account_list.find(".contact-list-item");

        function no_propagation(event) {
          event.stopPropagation();
        }

        list_item.find(".info-header")
          .hover(function() {
            $(this).find(".button-group").slideDown("fast");
          }, function() {
            $(this).find(".button-group").slideUp("fast", function() {
              $(this).find("#delete").children(".text").text("");
              $(this).find("#edit").show();
              $(this).find(".send").show();
            });
          });

        list_item.find(".body").click(no_propagation);

        list_item.map(function() {
          var list_items = $(".contact-list").find(".contact-list-item");
          var list_item = $(this);
          var body = list_item.find(".body");
          var info_body = body.find(".info-body");
          var list_body = body.find(".list-body");
          var info_header = list_item.find(".info-header");
          var edit_header = list_item.find(".edit-header");

          var delete_button = info_header.find("#delete");
          var edit_button = info_header.find("#edit");
          var send_button = info_header.find(".send");

          list_item.find(".button-group").hide();

          function change_account_item_state(state) {
            list_item.state = state;
            if (state == "info") {
              body.slideUp(0);
              edit_header.slideUp(0);
              info_header.slideDown("fast");
              
            } else if (state == "edit") {
              info_header.slideUp(0);
              edit_header.slideDown("fast");
              body.show();
              list_body.slideUp(0);
              info_body.slideDown("fast");
              account_list_wrapper.scrollTop(list_item.offset());
            } else if (state == "list") {
              info_header.slideDown("fast");
              edit_header.slideUp(0);
              body.show();
              info_body.slideUp(0);
              list_body.slideDown("fast");
            }
          }

          list_item.click(function() {
            change_account_item_state(list_item.state == "list" ? "info" : "list");
          })

          change_account_item_state("info", 0);

          edit_button.click(function(event) {
            change_account_item_state("edit");
            event.stopPropagation();
          });

          function blink(element, speed) {
            return element.fadeOut(speed).fadeIn(speed);
          }

          delete_button.click(function(event) {
            if (delete_button.children(".text").text() == "Really?") {
              list_item.slideUp("normal");
            } else {
              delete_button.children(".text").text("Really?");
              edit_button.hide();
              send_button.hide();
              blink(delete_button, "slow");
            }
            event.stopPropagation();
          });

          edit_header.find("#save").click(function() {
            change_account_item_state("info");
          });
          edit_header.find("#cancel").click(function() {
            change_account_item_state("info");
          });
        });
      }
      contact_list_init();
    })
