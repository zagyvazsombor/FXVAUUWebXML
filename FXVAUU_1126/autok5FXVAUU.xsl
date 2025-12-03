<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Autótípusok és példányszámuk:</h2>
                <ul>
                    <xsl:for-each select="autok/auto[not(tipus = preceding-sibling::auto/tipus)]">
                        <!-- Példányszám kiszámolása az adott típushoz -->
                        <xsl:variable
                            name="count" select="count(../auto[tipus=current()/tipus])" />
                        <li>
        Típus: <xsl:value-of select="tipus" /> - Darabszám: <xsl:value-of select="$count" />
                        </li>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>