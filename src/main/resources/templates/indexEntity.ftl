<#-- @ftlvariable name="entities" type="kotlin.collections.List<com.example.models.Entity>"-->
<#import "_layout.ftl" as layout />
<@layout.header>
    <#list entities?reverse as entity>
        <div>
            <h3>
                <a href="/entities/${entity.id}">Campo: ${entity.name}</a>
            </h3>
            <p>Value: ${entity.value}</p>
            <p>SectionId: ${entity.sectionId}</p>
            <p>Description: ${entity.description}</p>
            <p>Order: ${entity.order}</p>
            <p>IdArticle: ${entity.idArticle}</p>
        </div>

    </#list>


    <hr>
    <p>
        <a href="/entities/new">Create entity</a>
    </p>
</@layout.header>