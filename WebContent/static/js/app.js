$( document ).ready(function()
{
	$("time").timeago();
	
	$("a[href='#login']").click(function ()
	{
		$("body").addClass("modal-show");
	});
	
	$(".hide-modal").click(function ()
	{
		$("body").removeClass("modal-show");
		return false;
	});
	
	$(".filters input[type='checkbox']").click(function ()
	{
		$(".filters").submit();
	});
	
	$(".filters select").change(function ()
	{
		$(".filters").submit();
	});
	
	$(".bestAnswer:not(.checked)").click(function()
	{
		$that = $(this);
		$.get($("a", this).attr("href"), "", function (data)
		{
			$that.addClass("checked");
			$(".bestAnswer:not(.checked)").hide();
		});
		
		return false;
	});
	
//	$(".delete").click(function()
//	{
//		$.get($("a", this).attr("href"), "", function (data)
//		{
//			location.reload();
//		});
//			
//		return false;
//	});
	
	$(".voteWrapper a").click(function ()
	{
		$link = $(this);
		$that = $(this).parent();
		var isChedked = $(".vote", this).hasClass("rated");

		
		if ( $(this).parent().hasClass("enabled") )
		{
			$.post( $(this).attr("href"), $(this).attr("href").split("?")[1],
				function(data)
				{
					$(".voteScore", $that).text(data);
					$(".vote", $that).removeClass("rated");
					
					if (!isChedked)
					{
						$(".vote", $link).addClass("rated");
					}
					
				}
			);
		}
		else {
			$("body").addClass("modal-show");
		}
		
		return false;
	});
	
	$("#signup").submit(function ()
	{
		$(".error", this).removeClass("show");
		$that = $(this);
		
		$.post($(this).attr("action"), {email: $("input[name='email']", this).val(),
										password: $("input[name='password']", this).val(),
										passwordConfirm: $("input[name='passwordConfirm']", this).val()},
			function (data, status)
			{
				if (status === "success" && data === "ok")
				{
					location.reload();
				}
				else
				{
					$(".error", $that).text(data);
					$(".error", $that).addClass("show");
				}
			}
		);

		return false;
	});
	
	$("#loginForm").submit(function ()
	{
		$(".error", this).removeClass("show");
		$that = $(this);
		
		$.post($(this).attr("action"), {email: $("input[name='email']", this).val(),
										password: $("input[name='password']", this).val()},
			function (data, status)
			{
				if (status === "success" && data === "ok")
				{
					location.reload();
				}
				else
				{
					$(".error", $that).addClass("show");
				}
			}
		);

		return false;
	});
	
	$(".answerForm form").submit(function()
	{
		$that = $(this);
				
		$.post($(this).attr("action"), {content: $("#wmd-preview", this).html()},
			function (data, status)
			{
				location.reload();
			}
		);

		return false;
	});
	
	$("#questionForm").submit(function()
	{
		$(".error", this).removeClass("show");
		$that = $(this);
		
		$tagList = $("#tags");
		
		var tags = [];
		$(".question-tag", $tagList).each(function(i, element)
		{
			tags.push($(element).text().slice(0, -1));
		});
		
		$.post($(this).attr("action"), {title: $("input[name='title']", this).val(),
										content: $("#wmd-preview", this).html(),
										tags: tags.join()},
			function (data, status)
			{
											
											
				if (status === "success" && data.split(" ")[0] === "ok")
				{
					location.href = data.split(" ")[1];
				}
				else
				{
					$(".error", $that).text(data);
					$(".error", $that).addClass("show");
				}
			}
		);

		return false;
	});
	
	$("#questionForm input").bind("keypress", function(e)
	{
		return e.keyCode != 13;
	});
	
	$("#addTag").bind("keyup", function(e)
	{
		if (e.keyCode == 13)
		{
			$tagList = $("#tags");
			
			var tagName = $(this).val();
			var isValid = tagName.length >= 3;

			if (isValid)
			{
				var tags = [];
				$(".question-tag", $tagList).each(function(i, element)
				{
					tags.push($(element).text().slice(0, -1));
				});

				isValid = isValid && (tags.indexOf(tagName) == -1);
			}

			if (isValid)
			{
				$tagList.append("<span class=\"question-tag\">" + tagName + "<span class=\"x\">x</span></span>");
				$("#questionForm .error").removeClass("show");
			}
			else
			{
				$("#questionForm .error").text("Invalid tag.");
				$("#questionForm .error").addClass("show");
			}
			
			$(this).val("");

			return false;
			
		}
	});
	
	$("#tags").on("click", ".question-tag .x", function ()
	{
		$(this).parent().remove();
	});
	
});