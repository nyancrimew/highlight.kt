package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.Keyword
import ch.deletescape.highlight.core.Mode
import ch.deletescape.highlight.core.hljs

/*
Language = Microtik RouterOS script
Author = Ivan Dementev <ivan_div@mail.ru>
Description = Scripting host provides a way to automate some router maintenance tasks by means of executing user-defined scripts bounded to some event occurrence
URL = https = //wiki.mikrotik.com/wiki/Manual:Scripting
*/
// Colors from RouterOS terminal = 
//   green        - #0E9A00
//   teal         - #0C9A9A
//   purple       - #99069A
//   light-brown  - #9A9900

/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/routeros.js MD5 <6caa650eda28855908c68c6586af7a5b>
 */
internal fun routeros(): Mode {
    var STATEMENTS = "foreach do while for if from to step else on-error and or not in"
    // Global commands: Every global command should start with ":" token, otherwise it will be treated as variable.
    var GLOBAL_COMMANDS =
        "global local beep delay put len typeof pick log time set find environment terminal error execute parse resolve toarray tobool toid toip toip6 tonum tostr totime"
    // Common commands: Following commands available from most sub-menus:
    var COMMON_COMMANDS = "add remove enable disable set get print export edit find run debug error info warning"
    var LITERALS = "true false yes no nothing nil null"
    var OBJECTS =
        "traffic-flow traffic-generator firewall scheduler aaa accounting address-list address align area bandwidth-server bfd bgp bridge client clock community config connection console customer default dhcp-client dhcp-server discovery dns e-mail ethernet filter firewall firmware gps graphing group hardware health hotspot identity igmp-proxy incoming instance interface ip ipsec ipv6 irq l2tp-server lcd ldp logging mac-server mac-winbox mangle manual mirror mme mpls nat nd neighbor network note ntp ospf ospf-v3 ovpn-server page peer pim ping policy pool port ppp pppoe-client pptp-server prefix profile proposal proxy queue radius resource rip ripng route routing screen script security-profiles server service service-port settings shares smb sms sniffer snmp snooper socks sstp-server system tool tracking type upgrade upnp user-manager users user vlan secret vrrp watchdog web-access wireless pptp pppoe lan wan layer7-protocol lease simple raw"
    // print parameters
    // Several parameters are available for print command:
    // ToDo: var PARAMETERS_PRINT = "append as-value brief detail count-only file follow follow-only from interval terse value-list without-paging where info";
    // ToDo: var OPERATORS = "&& and ! not || or in ~ ^ & << >> + - * /";
    // ToDo: var TYPES = "num number bool boolean str string ip ip6-prefix id time array";
    // ToDo: The following tokens serve as delimiters in the grammar: ()  listOf()  {}  :   ;   $   /

    var VAR_PREFIX = "global local set for foreach"
    var VAR = Mode(
        className = "variable",
        variants = listOf(
            Mode(
                begin =
                    """${'$'}[\w\d#@][\w\d_]*"""
            ),
            Mode(
                begin =
                    """${'$'}\{(.*?)}"""
            )
        )
    )

    var QUOTE_STRING = Mode(
        className = "string",
        begin =
            """"""",
        end =
            """"""",
        contains = listOf(
            hljs.BACKSLASH_ESCAPE,
            VAR,
            Mode(
                className = "variable",
                begin =
                    """${'$'}\(""",
                end =
                    """\)""",
                contains = listOf(hljs.BACKSLASH_ESCAPE)
            )
        )
    )

    var APOS_STRING = Mode(
        className = "string",
        begin =
            """'""",
        end =
            """'"""
    )

    var IPADDR =
        "((25[0-5]|(2[0-4]|1{0,1}[0-9)){0,1}[0-9))\\.){3}(25[0-5]|(2[0-4]|1{0,1}listOf(0-9)){0,1}listOf(0-9))\\b"
    var IPADDR_wBITMASK = IPADDR + "/(3[0-2]|[1-2][0-9]|\\d)"
    // ////////////////////////////////////////////////////////////////////
    return Mode(
        aliases = listOf(
            "routeros",
            "mikrotik"
        ),
        case_insensitive = true,
        lexemes =
            """:?[\w-]+""",
        keywords = listOf(
            Keyword(
                className = "literal",
                value = LITERALS
            ),
            Keyword(
                className = "keyword",
                value = STATEMENTS + " :" +
                    STATEMENTS.split(" ").joinToString(" :") +
                    " :" +
                    GLOBAL_COMMANDS.split(" ").joinToString(" :")
            )
        ),
        contains = listOf(
            Mode(// недопустимые конструкции
                variants = listOf(
                    Mode(
                        begin =
                            """^@""",
                        end =
                            """${'$'}"""
                    ),
                    // dns
                    Mode(
                        begin =
                            """\/\*""",
                        end =
                            """\*\/"""
                    ),
                    // -- comment
                    Mode(
                        begin =
                            """%%""",
                        end =
                            """${'$'}"""
                    ),
                    // -- comment
                    Mode(
                        begin =
                            """^'""",
                        end =
                            """${'$'}"""
                    ),
                    // Monkey one line comment
                    Mode(
                        begin =
                            """^\s*\/[\w-]+=""",
                        end =
                            """${'$'}"""
                    ),
                    // jboss-cli
                    Mode(
                        begin =
                            """\/\/""",
                        end =
                            """${'$'}"""
                    ),
                    // Stan comment
                    Mode(
                        begin =
                            """^\[\<""",
                        end =
                            """\>\]${'$'}"""
                    ),
                    // F# class declaration?
                    Mode(
                        begin =
                            """<\/""",
                        end =
                            """>"""
                    ),
                    // HTML tags
                    Mode(
                        begin =
                            """^facet """,
                        end =
                            """\}"""
                    ),
                    // roboconf - лютый костыль )))
                    Mode(
                        begin = "^1\\.\\.(\\d+)${'\$'}",
                        end =
                            """${'$'}"""
                    ) // tap
                ),
                illegal =
                    """."""
            ),
            hljs.COMMENT(
                "^#",
                "${'\$'}"
            ),
            QUOTE_STRING,
            APOS_STRING,
            VAR,
            Mode(// attribute=value
                begin =
                    """[\w-]+\=([^\s\{\}\[\]\(\)]+)""",
                relevance = 0,
                returnBegin = true,
                contains = listOf(
                    Mode(
                        className = "attribute",
                        begin =
                            """[^=]+"""
                    ),
                    Mode(
                        begin =
                            """=""",
                        endsWithParent = true,
                        relevance = 0,
                        contains = listOf(
                            QUOTE_STRING,
                            APOS_STRING,
                            VAR,
                            Mode(
                                className = "literal",
                                begin = "\\b(" +
                                    LITERALS.split(" ").joinToString("|") +
                                    ")\\b"
                            ),
//              /*{
//                // IPv4 addresses and subnets
//                className = "number",
//                variants = listOf(
//                  Mode(begin = IPADDR_wBITMASK+"(,
// "+IPADDR_wBITMASK+\")*"), //192.168.0.0/24,1.2.3.0/24
//                  Mode(begin = IPADDR+"-"+IPADDR),       // 192.168.0.1-192.168.0.3
//                  Mode(begin = IPADDR+"(,
// "+IPADDR+\")*") // 192.168.0.1,192.168.0.34,192.168.24.1,192.168.0.1
//                )
//              ), //
//              /*{
//                // MAC addresses and DHCP Client IDs
//                className = "number",
//                begin = """\b(1 = )?([0-9A-Fa-f]{1,2}listOf(:-)){5}(listOf(0-9A-Fa-f)){1,2}\b"""
//              ), //*/
                            Mode(
                                // Не форматировать не классифицированные значения. Необходимо для исключения подсветки значений как built_in.
                                // className: "number",
                                begin =
                                    """("[^\"]*\"|[^\s\{\}\[\]]+)"""
                            ) // */
                        )
                    ) // */
                )
            ),
            // */
            Mode(
                // HEX values
                className = "number",
                begin =
                    """\*[0-9a-fA-F]+"""
            ),
            // */

            Mode(
                begin = "\\b(" +
                    COMMON_COMMANDS.split(" ").joinToString("|") + ")([\\s[(]|])",
                returnBegin = true,
                contains = listOf(
                    Mode(
                        className = "builtin-name",
                        // 'function",
                        begin =
                            """\w+"""
                    )
                )
            ),

            Mode(
                className = "built_in",
                variants = listOf(
                    Mode(
                        begin = "(\\.\\./|/|\\s)((" +
                            OBJECTS.split(" ").joinToString("|") +
                            ");?\\s)+",
                        relevance = 10
                    ),
                    Mode(
                        begin =
                            """\.\."""
                    )
                )
            ) // */
        )
    )
}
