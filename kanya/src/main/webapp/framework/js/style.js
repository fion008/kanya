(function($) {
  'use strict';

  $(function() {
    var $fullText = $('.admin-fullText');
    $('#admin-fullscreen').on('click', function() {
      $.AMUI.fullscreen.toggle();
    });

    $(document).on($.AMUI.fullscreen.raw.fullscreenchange, function() {
      $.AMUI.fullscreen.isFullscreen ? $fullText.text('关闭全屏') : $fullText.text('开启全屏');
    });

    $(document).ready(function(){
      var active=$('.tr-main-container').attr('id');
      $('#tr-header-nav .tr-nav').children('li.'+active).addClass('am-active');
    });
  });
})(jQuery);

(function($) {
  if ($.AMUI && $.AMUI.validator) {
    $.AMUI.validator.patterns.mobile = /^1((3|5|8){1}\d{1}|70|77)\d{8}$/;
    $.AMUI.validator.patterns.valitecode = /^\d{4}$/;
    $.AMUI.validator.patterns.tremail = /^[\w._]+@(tairanchina|bizbglobal|wydatas|yueduworld)\.[cC][oO][mM]$/;
  }
})(jQuery);

$.ajaxSetup({
  dataType: "json",  
  beforeSend: function(xhr, settings){  
      var csrftoken = $.AMUI.utils.cookie.get('csrftoken'); 
      xhr.setRequestHeader("X-CSRFToken", csrftoken);
  },  
});

	
