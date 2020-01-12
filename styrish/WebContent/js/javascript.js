var globalData=[];
var totalQuestion=[];
var currentQuestion=0;
var selected=[];
var header="";
function response(){
var skip=0;
var total=globalData.length;
for(i=1;i<selected.length;i++)
   {
      if(selected[i].length==0)
      skip++;
   }
var notvisited=total-currentQuestion;
if(selected.length>0)
var attempt=selected.length-1-skip;
else
var attempt=0;
var body= `<table id="summary"  style="width:100%" class="table table-bordered summary">
    <thead class="summary_thead">
    <tr>
    <td>Section</td>
    <td>Attempted</td>
    <td>Skipped</td>
    <td>Not Visited</td>
    <td>Total</td>
    </tr>
    </thead>
    <tbody>
    <tr>
    <td>${header}</td>
    <td>${attempt}</td>
    <td>${skip}</td>
    <td>${notvisited}</td>
    <td>${total}</td>
    </tr>
    </tbody>
  </table>`;
  $("#response").append(body);
}
function endTest(){
     if(confirm("Are you sure to submit test?"))
       {
            $.ajax({
              type: "POST",
              url: "http://fakerestapi.azurewebsites.net",
              dataType: "jsonp",
              data : JSON.stringify(selected),
              success: function(data) {
                console.log(data);
                document.getElementById("openModel").click();
                response();
              },
              complete:function(){
                document.getElementById("openModel").click();
                response();
              }
          });            
      }

}
function next(){

  choices=globalData[currentQuestion]['choices'];
  choosen=[];
  for(i=0;i<choices.length; i++){
      id="choice_"+(i+1);
      v= document.getElementById(id).checked;
      if(v)
      choosen.push(i+1);
  }
  selected[currentQuestion+1]=choosen;
  currentQuestion++;
  if(globalData.length<=currentQuestion)
     {
      document.getElementById("next").disabled = true;
      return;
     }
  getQuestion();
}

function setHeader(header)
{
  document.getElementById("subject_header").innerHTML=header;
  document.getElementById("subject_header").style.display="block";

}
function getQuestion(){
     $("#frame").empty();
    document.getElementById("frame").style.display="block";

     var body=`<div id="question_div"><table id="question"><tr><td><b> Question ${currentQuestion+1}.</b></td></tr><tr>`;
      body+=`<td id ="question_td">${globalData[currentQuestion]['question']}</td>`;
      body+=`<td id = "choices_td">`;
      id="choice-block-" + (currentQuestion+1);
       body+=`<table id=${id} class="mcq_choice">`;
       choices=globalData[currentQuestion]['choices'];
       for(i=0;i<choices.length; i++)
                 body+=`<tr><td data-index=${i+1} class="choice"><label><input type="checkbox" id=choice_${i+1}>${choices[i]}</label></td></tr>`;
    body+=`</table></td></tr></table>
    <p class="warning">Choose more than one option and then select <b>Done</b>. To deselect an option, choose it a second time.</p>
    </div><div><center class="done-btn"><button class="btn btn-info" id="next" onclick="next()">Done</button></center>
    </div>`;
    $("#frame").append(body);

}

function getapidata(){
      $.ajax({
        type: "GET",
        url: "http://www.mocky.io/v2/5e09c98830000057002444a9",
        crossDomain: true,
        dataType: "jsonp",
        success: function(data) {
          setHeader(data[0]["subject"]);
          header=data[0]["subject"];
          globalData=data[1];
          totalQuestion= globalData.length;
          getQuestion();
        }
    });
}
jQuery(document).ready(function($){
        document.getElementById("subject_header").style.display="none";
        document.getElementById("frame").style.display="none";
        getapidata();
});

  