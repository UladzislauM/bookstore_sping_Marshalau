for (
  let index = 1;
  index < $('table >tbody >tr').length + 1;
  index++
) {
$(`#book_${index}`).click(function () {
    //click
    $.ajax({
      url: `/cart/book_to_cart`,
      method: "post",
      dataType: "html",
      data: { id: index },
      success: function (data) {
        if ($(data).filter("title").text() === "Login in BookStore") {
          window.location.href = "/login";
        } else {
          /*Open dialog*/
          var target = $(this);
          $(".dialog")
            .text(`${$(`#book_title_${index}`).text()} - add to cart`)
            .css("display", "block")
            .animate({ opacity: 1, top: "10%" }, 400);
          /*Close dialog*/
          setTimeout(function () {
            $(".dialog").animate({ opacity: 0, top: "35%" }, 490, function () {
              $(this).css("display", "none");
            });
          }, 3000);
        }
      },
      error: function (error) {
        if (error.status === 0) {
          alert("Not connect. Verify Network.");
        } else if (error.status == 404) {
          alert("Requested page not found (404).");
        } else if (error.status == 500) {
          alert("Internal Server Error (500).");
        } else if (error === "parsererror") {
          alert("Requested JSON parse failed.");
        } else if (error === "timeout") {
          alert("Time out error.");
        } else if (error === "abort") {
          alert("Ajax request aborted.");
        } else {
          alert("Uncaught Error. " + error.responseText);
        }
      },
    });
  });
}
