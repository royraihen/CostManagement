
function CircleBarIncome(e) {
  $(e)
    .circleProgress({ fill: { color: "#00EAFF" } })
    .on("circle-animation-progress", function(_event, _progress, stepValue) {
      $(this)
        .find("strong")
        .text(String(parseFloat(stepValue).toFixed(3)*100) + "%")

    });
}

function CircleBarSpend(e) {
    $(e)
        .circleProgress({ fill: { color: "#FF3333" } })
        .on("circle-animation-progress", function(_event, _progress, stepValue) {
            $(this)
                .find("strong")
                .text(String(parseFloat(stepValue).toFixed(3)*100) + "%")

        });
}
CircleBarIncome(".round");
CircleBarSpend(".spend");

