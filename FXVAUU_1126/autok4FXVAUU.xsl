<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Miskolci tulajdonosok autóinak rendszámai:</h2>
                <ul>
                    <xsl:for-each select="autok/auto[tulaj/varos='Miskolc']">

                        <li> Rendszám: <xsl:value-of select="@rsz" />
                        </li>

                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>