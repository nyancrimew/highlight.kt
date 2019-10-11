package ch.deletescape.highlight.languages

import ch.deletescape.highlight.core.Keyword
import ch.deletescape.highlight.core.Mode
import ch.deletescape.highlight.core.hljs

/*
Language = pf
Category = config
Author = Peter Piwowarski <oldlaptop654@aol.com>
Description = The pf.conf(5) format as of OpenBSD 5.6
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/pf.js MD5 <a3cee2511362adf55bd55e6a4bf71109>
 */
internal fun pf(): Mode {
    var MACRO = Mode(
        className = "variable",
        begin =
            """${'$'}[\w\d#@][\w\d_]*"""
    )
    var TABLE = Mode(
        className = "variable",
        begin =
            """<(?!\/)""",
        end =
            """>"""
    )
    var QUOTE_STRING = Mode(
        className = "string",
        begin =
            """"""",
        end =
            """""""
    )
    return Mode(
        aliases = listOf("pf.conf"),
        lexemes =
            """[a-z0-9_<>-]+""",
        keywords = listOf(
            Keyword(
                className = "built_in",
                value = /* block match pass are "actions" in pf.conf(5)), the rest are
                 * lexically similar top-level commands.
                 */
                    "block match pass load anchor|5 antispoof|10 set table"
            ),
            Keyword(
                className = "keyword",
                value =
                    "in out log quick on rdomain inet inet6 proto from port os to routeallow-opts divert-packet divert-reply divert-to flags group icmp-typeicmp6-type label once probability recieved-on rtable prio queuetos tag tagged user keep fragment for os dropaf-to|10 binat-to|10 nat-to|10 rdr-to|10 bitmask least-stats random round-robinsource-hash static-portdup-to reply-to route-toparent bandwidth default min max qlimitblock-policy debug fingerprints hostid limit loginterface optimizationreassemble ruleset-optimization basic none profile skip state-defaultsstate-policy timeoutconst counters persistno modulate synproxy state|5 floating if-bound no-sync pflow|10 sloppysource-track global rule max-src-nodes max-src-states max-src-connmax-src-conn-rate overload flushscrub|5 max-mss min-ttl no-df|10 random-id"
            ),
            Keyword(
                className = "literal",
                value =
                    "all any no-route self urpf-failed egress|5 unknown"
            )
        ),
        contains = listOf(
            hljs.HASH_COMMENT_MODE,
            hljs.NUMBER_MODE,
            hljs.QUOTE_STRING_MODE,
            MACRO,
            TABLE
        )
    )
}
