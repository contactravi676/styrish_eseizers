$(document).ready(function(){
    $(".dropdown, .btn-group").hover(function(){
        var dropdownMenu = $(this).children(".dropdown-menu");
        if(dropdownMenu.is(":visible")){
            dropdownMenu.parent().toggleClass("open");
        }
    });
}); 

function  k(){
	setTimeout(function() {
	  $('.a').tooltip({trigger:'manual'}).tooltip('show');
	}, 1000);
	setTimeout(function() {
	  $('.a').tooltip({trigger:'manual'}).tooltip('hide');
	}, 2000);

	setTimeout(function() {
	 $('.b').tooltip({trigger:'manual'}).tooltip('show');
	}, 2000);
	setTimeout(function() {
	 $('.b').tooltip({trigger:'manual'}).tooltip('hide');
	}, 3000);
	
	setTimeout(function() {
	 $('.c').tooltip({trigger:'manual'}).tooltip('show');
	}, 3000);
	setTimeout(function() {
	 $('.c').tooltip({trigger:'manual'}).tooltip('hide');
	}, 4000);
	
	setTimeout(function() {
	 $('.d').tooltip({trigger:'manual'}).tooltip('show');
	}, 4000);
	setTimeout(function() {
	 $('.d').tooltip({trigger:'manual'}).tooltip('hide');
	}, 5000);		
}
//$('.tx').tooltip({trigger:'manual'}).tooltip('hide');

/* TO hide tooltip while menu is open */


/* hiding tooltip over */

$(document).ready(function(){$('[data-toggle="t1"]').tooltip();});
	$(document).ready(function(){$('[data-toggle="t2"]').tooltip();});
	$(document).ready(function(){$('[data-toggle="t3"]').tooltip();});
	$(document).ready(function(){$('[data-toggle="t4"]').tooltip();});



/*FOR SMOOTH JUMP TO SECOND PORTION */
window.smoothScroll = function(target) {
    var scrollContainer = target;
    do { //find scroll container
        scrollContainer = scrollContainer.parentNode;
        if (!scrollContainer) return;
        scrollContainer.scrollTop += 1;
    } while (scrollContainer.scrollTop == 0);
    
    var targetY = 0;
    do { //find the top of target relatively to the container
        if (target == scrollContainer) break;
        targetY += target.offsetTop;
    } while (target = target.offsetParent);
    
    scroll = function(c, a, b, i) {
        i++; if (i > 30) return;
        c.scrollTop = a + (b - a) / 30 * i;
        setTimeout(function(){ scroll(c, a, b, i); }, 20);
    }
    // start scrolling
    scroll(scrollContainer, scrollContainer.scrollTop, targetY, 0);
}
	
	