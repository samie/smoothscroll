window.org_vaadin_example_smoothscroll_SmoothScrollExtension = function() {
	
    "use strict";

    this.parentElement = this.getElement(this.getParentId());
    this.scrollerElem = this.parentElement.getElementsByClassName("v-scrollable")[0];
    
    this.scollToComponent = function(id, duration) {
    	var c = this.getElement(id);
    	var s = this.scrollerElem;
        var initialScrollTop = s.scrollTop;
        var targetScrollTop = c.offsetTop;
        var scrollAmount = targetScrollTop - initialScrollTop;
        var startTime = null;      
        
        //window.alert("scroll "+scrollAmount+"px in "+duration+"ms");
        function animate(timestamp) {
            if (startTime === null) {
                startTime = timestamp;                
            }
            var remainingTime = duration - (timestamp - startTime);
            
            if (remainingTime < 50) {   
            	s.scrollTop = targetScrollTop;
                return;
              } else {
                var scrollRate = 1 - Math.pow(remainingTime/duration, 3);  // easing                 
                s.scrollTop = initialScrollTop + (scrollRate * scrollAmount);
                window.requestAnimationFrame(animate);
              }

        };
        animate(null);
    };
};