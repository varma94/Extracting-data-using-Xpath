<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
  <h2>Ebay Unites Collection</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th>id</th>
      <th>Source</th>
      <th>Name</th>
      <th>Min Price</th>
      <th>Full Description</th>
    </tr>
    <xsl:for-each select="//categories/category/items/product">
    <tr>
      <td><xsl:value-of select="@id"/></td>
      <td><xsl:value-of select="images/image/sourceURL"/></td>
      <td><xsl:value-of select="name"/></td>
      <td><xsl:value-of select="minPrice"/></td>
       <td><xsl:value-of select="fullDescription"/></td>
    </tr>
    </xsl:for-each>
  </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>