package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = Puppet
Author = Jose Molina Colmenero <gaudy41@gmail.com>
Category = config
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/puppet.js MD5 <4088d2e59dea5b81a61dd9b9bd965acb>
 */
internal fun puppet(): Mode {
    var PUPPET_KEYWORDS = listOf(
        Keyword(
            className = "keyword",
            value =
                /* language keywords */
                "and case default else elsif false if in import enherits node or true undef unless main settings \$string "
        ),
        Keyword(
            className = "literal",
            value =
                /* metaparameters */
                "alias audit before loglevel noop require subscribe tag owner ensure group mode name|0 changes context force incl lens load_path onlyif provider returns root show_diff type_check en_address ip_address realname command environment hour monute month monthday special target weekday creates cwd ogoutput refresh refreshonly tries try_sleep umask backup checksum content ctime force ignore links mtime purge recurse recurselimit replace selinux_ignore_defaults selrange selrole seltype seluser source souirce_permissions sourceselect validate_cmd validate_replacement allowdupe attribute_membership auth_membership forcelocal gid ia_load_module members system host_aliases ip allowed_trunk_vlans description device_url duplex encapsulation etherchannel native_vlan speed principals allow_root auth_class auth_type authenticate_user k_of_n mechanisms rule session_owner shared options device fstype enable hasrestart directory present absent link atboot blockdevice device dump pass remounts poller_tag use message withpath adminfile allow_virtual allowcdrom category configfiles flavor install_options instance package_settings platform responsefile status uninstall_options vendor unless_system_user unless_uid binary control flags hasstatus manifest pattern restart running start stop allowdupe auths expiry gid groups home iterations key_membership keys managehome membership password password_max_age password_min_age profile_membership profiles project purge_ssh_keys role_membership roles salt shell uid baseurl cost descr enabled enablegroups exclude failovermethod gpgcheck gpgkey http_caching include includepkgs keepalive metadata_expire metalink mirrorlist priority protect proxy proxy_password proxy_username repo_gpgcheck s3_enabled skip_if_unavailable sslcacert sslclientcert sslclientkey sslverify mounted"
        ),
        Keyword(
            className = "built_in",
            value =
                /* core facts */
                "architecture augeasversion blockdevices boardmanufacturer boardproductname boardserialnumber cfkey dhcp_servers domain ec2_ ec2_userdata facterversion filesystems ldom fqdn gid hardwareisa hardwaremodel hostname id|0 interfaces ipaddress ipaddress_ ipaddress6 ipaddress6_ iphostnumber is_virtual kernel kernelmajversion kernelrelease kernelversion kernelrelease kernelversion lsbdistcodename lsbdistdescription lsbdistid lsbdistrelease lsbmajdistrelease lsbminordistrelease lsbrelease macaddress macaddress_ macosx_buildversion macosx_productname macosx_productversion macosx_productverson_major macosx_productversion_minor manufacturer memoryfree memorysize netmask metmask_ network_ operatingsystem operatingsystemmajrelease operatingsystemrelease osfamily partitions path physicalprocessorcount processor processorcount productname ps puppetversion rubysitedir rubyversion selinux selinux_config_mode selinux_config_policy selinux_current_mode selinux_current_mode selinux_enforced selinux_policyversion serialnumber sp_ sshdsakey sshecdsakey sshrsakey swapencrypted swapfree swapsize timezone type uniqueid uptime uptime_days uptime_hours uptime_seconds uuid virtual vlans xendomains zfs_version zonenae zones zpool_version"
        )
    )
    var COMMENT = hljs.COMMENT(
        "#",
        "\$"
    )
    var IDENT_RE = "([A-Za-z_]|::)(\\w|::)*"
    var TITLE = hljs.inherit(hljs.TITLE_MODE, Mode(begin = IDENT_RE))
    var VARIABLE = Mode(
        className = "variable",
        begin = "\\\$" +
            IDENT_RE
    )
    var STRING = Mode(
        className = "string",
        contains = listOf(hljs.BACKSLASH_ESCAPE, VARIABLE),
        variants = listOf(
            Mode(
                begin =
                    """'""",
                end =
                    """'"""
            ),
            Mode(
                begin =
                    """"""",
                end =
                    """""""
            )
        )
    )
    return Mode(
        aliases = listOf("pp"),
        contains = listOf(
            COMMENT,
            VARIABLE,
            STRING,
            Mode(
                beginKeywords = keywords("class"),
                end = "\\\\{|;",
                illegal =
                    """=""",
                contains = listOf(TITLE, COMMENT)
            ),
            Mode(
                beginKeywords = keywords("define"),
                end =
                    """\{""",
                contains = listOf(
                    Mode(
                        className = "section",
                        begin = hljs.IDENT_RE,
                        endsParent = true
                    )
                )
            ),
            Mode(
                begin = hljs.IDENT_RE + "\\s+\\\\{",
                returnBegin = true,
                end =
                    """\S""",
                contains = listOf(
                    Mode(
                        className = "keyword",
                        begin = hljs.IDENT_RE
                    ),
                    Mode(
                        begin =
                            """\{""",
                        end =
                            """\}""",
                        keywords = keywords(PUPPET_KEYWORDS),
                        relevance = 0,
                        contains = listOf(
                            STRING,
                            COMMENT,
                            Mode(
                                begin = "[a-zA-Z_]+\\s*=>",
                                returnBegin = true,
                                end = "=>",
                                contains = listOf(
                                    Mode(
                                        className = "attr",
                                        begin = hljs.IDENT_RE
                                    )
                                )
                            ),
                            Mode(
                                className = "number",
                                begin = "(\\b0[0-7_]+)|(\\b0x[0-9a-fA-F_]+)|(\\b[1-9][0-9_]*(\\.[0-9_]+)?)|[0_]\\b",
                                relevance = 0
                            ),
                            VARIABLE
                        )
                    )
                ),
                relevance = 0
            )
        )
    )
}
