<#-- @ftlvariable name="entities" type="kotlin.collections.List<com.example.models.Entity>" -->
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
<#list entities?reverse as entity>
    <div>
        <h3>
            <a href="/entities/${entity.id}">${entity.name}</a>
        </h3>
        <p>
            Description: ${entity.description}
        </p>
        <p>
            Value: ${entity.value}
        </p>
        <p>
            SectionId: ${entity.sectionId}
        </p>
        <p>
            Order: ${entity.order}
        </p>
    </div>
</#list>
<hr>
<p>
    <a href="/entities/newEntity">Create entity</a>
</p>
</body>
</html>
