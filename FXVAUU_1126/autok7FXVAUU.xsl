<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" indent="yes" />

    <xsl:template match="/">
        <autokRendezett>
            <xsl:for-each select="autok/auto">
                <!-- Árszerinti rendezés növekvő sorrendben -->
                <xsl:sort select="ar" data-type="number"
                    order="ascending" />
                <auto>
                    <rendszam>
                        <xsl:value-of select="@rsz" />
                    </rendszam>
                    <ar>
                        <xsl:value-of select="ar" />
                    </ar>
                </auto>
            </xsl:for-each>
        </autokRendezett>
    </xsl:template>

</xsl:stylesheet>