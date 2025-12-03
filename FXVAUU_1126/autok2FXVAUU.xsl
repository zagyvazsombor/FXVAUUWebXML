<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Mennyi autó drágább mint 30000</h2>
                <ul>
                    <xsl:for-each select="autok/auto[ar>30000]">

                        <li> Rendszám: <xsl:value-of select="@rsz" /> Ár: <xsl:value-of select="ar" />
        Ft
                        </li>

                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>