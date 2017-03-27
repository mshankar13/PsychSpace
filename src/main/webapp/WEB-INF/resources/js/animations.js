/**
 * Created by acerini on 3/25/2017.
 */
var fadeInReveal ={
    duration: 1000,
    delay: 100,
    useDelay: 'always',
    reset: true,
    scale: 1,
    easing: 'linear',
    distance: '0px'
};

var fadeInOnceReveal ={
    duration: 1000,
    delay: 100,
    useDelay: 'always',
    reset: false,
    scale: 1,
    easing: 'linear',
    distance: '0px'
};

window.sr = ScrollReveal();
sr.reveal('.fadein', fadeInReveal);
sr.reveal('.fadein-once', fadeInOnceReveal);