<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Az autók száma</h2>
                <p> Összesen: <xsl:value-of select="count(autok/auto)" /> db </p>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>