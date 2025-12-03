<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Az autók rendszámai és árai</h2>
                <ul>
                    <xsl:for-each select="autok/auto">
                        <xsl:sort select="ar" data-type="number" order="ascending" />
                        <li> Rendszám: <xsl:value-of
                                select="@rsz" /> Ár: <xsl:value-of select="ar" /> Ft </li>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>