<#-- @ftlvariable name="articles" type="kotlin.collections.List<com.example.models.Article>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Kotlin Journal</title>
</head>
<body style="text-align: center; font-family: sans-serif">
<img src="https://raw.githubusercontent.com/ktorio/ktor-documentation/a0c2a6d482bb8c8517119f882e9f85ff912f265f/codeSnippets/snippets/tutorial-website-static/src/main/resources/files/ktor_logo.png" alt="logo Ktor">
<h1>Kotlin Ktor Journal </h1>
<p><i>Powered by Ktor & Freemarker!</i></p>
<hr>
<#list articles?reverse as article>
    <div>
        <h3>
            <a href="/articles/${article.id}">${article.title}</a>
        </h3>
        <p>
            ${article.body}
        </p>
    </div>
</#list>
<hr>
<p>
    <a href="/articles/new">Create article</a>
</p>
</body>
</html>