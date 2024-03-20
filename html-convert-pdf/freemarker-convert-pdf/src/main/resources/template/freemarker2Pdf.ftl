<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Freemarker2Pdf</title>
</head>
<body>
<h1>${title}</h1>
<h2>作者：${poetName}</h2>
<#list contents as content>
    <p>${content}</p>
</#list>
</body>
</html>