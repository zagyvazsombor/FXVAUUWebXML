<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Autók városonként:</h2>
                <ul>
                    <!-- Végigmegyünk az összes tulaj városon, csak az első előfordulást vesszük -->
                    <xsl:for-each
                        select="autok/auto[not(tulaj/varos = preceding-sibling::auto/tulaj/varos)]">
                        <!-- Darabszám és összár számítása az adott városhoz -->
                        <xsl:variable
                            name="count" select="count(../auto[tulaj/varos=current()/tulaj/varos])" />
                        <xsl:variable
                            name="sum" select="sum(../auto[tulaj/varos=current()/tulaj/varos]/ar)" />

                        <li>
        Város: <xsl:value-of select="tulaj/varos" /> - Darabszám: <xsl:value-of select="$count" /> -
        Összár: <xsl:value-of select="$sum" />
                        </li>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>