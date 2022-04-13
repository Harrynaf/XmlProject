<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns2="https://www.w3schools.com">
    <xsl:template match="/ns2:book ">
        <html>
            <body>
                <table border="1">
                    <tr>
                        <th>dateTimeOfCreation</th>
                        <th>author</th>
                        <th>applicationClassName</th>
                        <th>chapters</th>
                        <th>paragraphs</th>
                        <th>lines</th>
                        <th>distinctWords</th>
                        <th>allWords</th>
                    </tr>
                    <xsl:for-each select="statistics">
                        <tr>
                            <td>
                                <xsl:value-of select="dateTimeOfCreation"/>
                            </td>
                            <td>
                                <xsl:value-of select="author"/>
                            </td>
                            <td>
                                <xsl:value-of select="applicationClassName"/>
                            </td>
                            <td>
                                <xsl:value-of select="chapters"/>
                            </td>
                            <td>
                                <xsl:value-of select="paragraphs"/>
                            </td>
                            <td>
                                <xsl:value-of select="lines"/>
                            </td>
                            <td>
                                <xsl:value-of select="distinctWords"/>
                            </td>
                            <td>
                                <xsl:value-of select="allWords"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>