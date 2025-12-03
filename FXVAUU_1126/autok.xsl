<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Autók listája</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Rendszám</th>
                        <th>Típus</th>
                        <th>Ár</th>
                        <th>Szín</th>
                        <th>Tulaj neve</th>
                        <th>Város</th>
                    </tr>
                    <xsl:for-each select="autok/auto">
                        <tr>
                            <td>
                                <xsl:value-of select="@rsz" />
                            </td>
                            <td>
                                <xsl:value-of select="tipus" />
                            </td>
                            <td>
                                <xsl:value-of select="ar" />
                            </td>
                            <td>
                                <xsl:value-of select="szin" />
                            </td>
                            <td>
                                <xsl:value-of select="tulaj/nev" />
                            </td>
                            <td>
                                <xsl:value-of select="tulaj/varos" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>