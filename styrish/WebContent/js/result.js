function data(globalData){
         datapoint=[];
         arr=[];
        for(i=0;i<globalData.length;i++)
          {
              arr=[];
            arr["y"]=(globalData[i].correct/globalData[i].Totalquestion)*100;
            arr["label"]=globalData[i].subject;
            datapoint.push(arr);
          }
         
        var chart = new CanvasJS.Chart("chartContainer1", {
            animationEnabled: true,
            data: [{
                type: "pie",
                startAngle: 240,
                yValueFormatString: "##0.00\"%\"",
                indexLabel: "{label} {y}",
                dataPoints: datapoint
            }]
        });
        chart.render();
        chart = new CanvasJS.Chart("chartContainer2", {
            animationEnabled: true,
            data: [{
                type: "bar",
                startAngle: 240,
                yValueFormatString: "##0.00\"%\"",
                indexLabel: "{label} {y}",
                dataPoints: datapoint
            }]
        });
        chart.render();

        var body="";
        
         for(i=0;i<globalData.length;i++)
         body+=` <div class="col-sm-4" id="subject_details">
         <h3>${globalData[i].subject}</h3>
         <hr>
          <p> we offer this course</p>
          <button class="btn btn-info" >view details</button>
       </div>`;
        
        $("#courses").append(body);
}

window.onload = function() {
    $.ajax({
        type: "GET",
        url: "http://fakerestapi.azurewebsites.net",
        dataType: "jsonp",
        success: function(data){       
            data();
        },
        complete: function(){
            //sample data
            var globalData=[];
            var arr=[];
            arr["subject"]="science";
            arr["Totalquestion"]=10;
            arr["correct"]=10;

            globalData.push(arr);
            arr=[];
            arr["subject"]="Math";
            arr["Totalquestion"]=10;
            arr["correct"]=5;
            globalData.push(arr);
            data(globalData);
        }
    }); 
    
 }